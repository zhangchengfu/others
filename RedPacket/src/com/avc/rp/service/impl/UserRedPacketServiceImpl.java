package com.avc.rp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.avc.rp.dao.RedPacketDao;
import com.avc.rp.dao.UserRedPacketDao;
import com.avc.rp.model.RedPacket;
import com.avc.rp.model.UserRedPacket;
import com.avc.rp.service.RedisRedPacketService;
import com.avc.rp.service.UserRedPacketService;

import redis.clients.jedis.Jedis;

@Service
public class UserRedPacketServiceImpl implements UserRedPacketService {

	@Autowired
	private UserRedPacketDao userRedPacketMapper = null;
	
	@Autowired
	private RedPacketDao redPacketMapper = null;
	
	@Autowired
	private RedisTemplate redisTemplate = null;
	
	@Autowired
	private RedisRedPacketService redisRedPacketService = null;
	
	//Lua脚本
	String script = "local listKey = 'red_packet_list_'..KEYS[1] \n"
					+"local redPacket = 'red_packet_'..KEYS[1] \n"
					+"local stock = tonumber(redis.call('hget',redPacket, 'stock')) \n"
					+"if stock <=0 then return 0 end \n"
					+"stock = stock-1 \n"
					+"redis.call('hset',redPacket,'stock',tostring(stock)) \n"
					+"redis.call('rpush',listKey, ARGV[1]) \n"
					+"if stock ==0 then return 2 end \n"
					+"return 1 \n";
	//在缓存Lua脚本后，使用该变量保存Redis返回的32位的SHA1编码， 使用它去执行缓存的Lua脚本
	String sha1 = null;
	
	//失败
	private final static int FALIED = 0;
	
	@Override
	@Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED)
	public int grapRedPacket(Integer redPacketId, Integer userId) {
		//获取红包信息
		RedPacket redPacket = redPacketMapper.getRedPacketForUpdate(redPacketId);
		//当前小红包库存大于0
		if(redPacket.getStock()>0) {
			redPacketMapper.decreaseRedPacket(redPacketId);
			//生成抢红包信息
			UserRedPacket userRedPacket = new UserRedPacket();
			userRedPacket.setRed_packet_id(redPacketId);
			userRedPacket.setUser_id(userId);
			userRedPacket.setAmount(redPacket.getUnit_amount());
			userRedPacket.setNote("抢红包"+redPacketId);
			int result = userRedPacketMapper.grapRedPacket(userRedPacket);
			return result;
		}
		
		return FALIED;
	}
	
	//@Override // 乐观锁 按时间戳重入
	@Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED)
	public int grapRedPacketForVersion(Integer redPacketId, Integer userId) {
		//记录开始时间
		long start = System.currentTimeMillis();
		
		while(true) {
			//获取循环当前时间
			long end  = System.currentTimeMillis();
			//当前时间超过100毫秒，返回失败
			if(end - start >100) {
				return FALIED;
			}
			
			//获取红包信息,留意version的值
			RedPacket redPacket = redPacketMapper.getRedPacketForUpdate(redPacketId);
			//当前红包库存大于0
			if(redPacket.getStock()>0) {
				// 再次传入线程保存的version旧值给Sql，是否有其他线程修改过数据
				int update = redPacketMapper.decreaseRedPacketForVersion(redPacketId, redPacket.getVersion());
				//如果没有数据更新，则说明其他线程已经修改过数据，本次失败，重新抢夺
				if(update == 0) {
					continue;
					//return FALIED;
				}
				//生成抢红包信息
				UserRedPacket userRedPacket = new UserRedPacket();
				userRedPacket.setRed_packet_id(redPacketId);
				userRedPacket.setUser_id(userId);
				userRedPacket.setAmount(redPacket.getUnit_amount());
				userRedPacket.setNote("抢红包"+redPacketId);
				
				int result = userRedPacketMapper.grapRedPacket(userRedPacket);
				return result;
			}else {
				//没有库存，立即返回失败 
				return FALIED;
			}
		}
	}
	
	//@Override //乐观锁  按次数冲入
	/*@Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED)
	public int grapRedPacketForVersion1(Integer redPacketId, Integer userId) {
		
		for(int i =0; i<3; i++) {
			//获取红包信息,留意version的值
			RedPacket redPacket = redPacketMapper.getRedPacketForUpdate(redPacketId);
			//当前红包库存大于0
			if(redPacket.getStock()>0) {
				// 再次传入线程保存的version旧值给Sql，是否有其他线程修改过数据
				int update = redPacketMapper.decreaseRedPacketForVersion(redPacketId, redPacket.getVersion());
				//如果没有数据更新，则说明其他线程已经修改过数据，本次失败，重新抢夺
				if(update == 0) {
					continue;
					//return FALIED;
				}
				//生成抢红包信息
				UserRedPacket userRedPacket = new UserRedPacket();
				userRedPacket.setRed_packet_id(redPacketId);
				userRedPacket.setUser_id(userId);
				userRedPacket.setAmount(redPacket.getUnit_amount());
				userRedPacket.setNote("抢红包"+redPacketId);
				
				int result = userRedPacketMapper.grapRedPacket(userRedPacket);
				return result;
			}else {
				//没有库存，立即返回失败 
				return FALIED;
			}
		}
		return FALIED;
	}*/

	@Override
	public Long grapRedPacketByRedis(Integer redPacketId, Integer userId) {
		//当前抢红包用户和日期信息
		String args = userId+"-"+System.currentTimeMillis();
		Long result = null;
		//获取底层Redis操作对象
		Jedis jedis = (Jedis) redisTemplate.getConnectionFactory().getConnection().getNativeConnection();
		try {
			//如果脚本没有加载过，那么进行加载，这样就会返回一个sha1编码
			if(sha1 == null) {
				sha1 = jedis.scriptLoad(script);
			}
			//执行脚本，返回结果
			Object res = jedis.evalsha(sha1,1,redPacketId+"",args);
			result = (Long)res;
			//返回2 时为最后一个红包，此时将抢红包信息通过异步保存到数据库
			if(result == 2) {
				//获取单个小金额红包
				String unitAmountStr = jedis.hget("red_packet_"+redPacketId, "unit_amount");
				//触发保存数据库操作
				Double unitAmount = Double.parseDouble(unitAmountStr);
				System.out.println("thread_name="+Thread.currentThread().getName());
				redisRedPacketService.saveUserRedPacketByRedis(redPacketId, unitAmount);
			}
		} finally {
			//确保jedis关闭
			if(jedis != null && jedis.isConnected()) {
				jedis.close();
			}
		}
		return result;
	}
}
