package com.example.demo.redis;

import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;


/**
 * @author AmVilCresx
 * 
 * Redis操作类
 * 这里只给出了常用的操作，可根据需求扩展
 * */
@Component
public class RedisOperator {
	
	@Resource
	private RedisTemplate<String, Object> redisTemplate;
	
	/**
	 * 简单K-V操作
	 * */
	@Resource
    private ValueOperations<String,Object> valueOperations;

	/**
	 * 针对map类型的数据操作
	 * */
    @Autowired
    private HashOperations<String, String, Object> hashOperations;

    /**
     * 针对list类型的数据操作
     * */
    @Autowired
    private ListOperations<String, Object> listOperations;

    /**
     * set类型数据操作
     * */
    @Autowired
    private SetOperations<String, Object> setOperations;

    /**
     * zset类型数据操作
     * */
    @Autowired
    private ZSetOperations<String, Object> zSetOperations;

	/**
	 * 写入缓存，并设置有效时间
	 * */
	public boolean set(final String key, Object value,Long expireTime) {
		boolean result = false;
		try {
			valueOperations.set(key, value);
			redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
			result = true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	 
    /**
     * 读取缓存
     * @param key
     * @return
     */
    public Object get(final String key) {
        Object result = null;
        result = valueOperations.get(key);
        return result;
    }	

   /**
     * 判断缓存中是否有对应的value
     * @param key
     * @return
     */
    public boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }
	
	/**
     * 删除对应的value
     * @param key
     */
    public void remove(final String key) {
        if (exists(key)) {
            redisTemplate.delete(key);
        }
    }	
     
    /**
     * 重名名key，如果newKey已经存在，则newKey的原值被覆盖
     *
     * @param oldKey
     * @param newKey
     */
    public void renameKey(String oldKey, String newKey) {
        redisTemplate.rename(oldKey, newKey);
    }

    /**
     * newKey不存在时才重命名
     *
     * @param oldKey
     * @param newKey
     * @return 修改成功返回true
     */
    public boolean renameKeyNotExist(String oldKey, String newKey) {
        return redisTemplate.renameIfAbsent(oldKey, newKey);
    }

    /**
     * 删除key
     *
     * @param key
     */
    public void deleteKey(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 删除多个key
     *
     * @param keys
     */
    public void deleteKey(String... keys) {
        Set<String> kSet = Stream.of(keys).map(k -> k).collect(Collectors.toSet());
        redisTemplate.delete(kSet);
    }

    /**
     * 删除Key的集合
     *
     * @param keys
     */
    public void deleteKey(Collection<String> keys) {
        Set<String> kSet = keys.stream().map(k -> k).collect(Collectors.toSet());
        redisTemplate.delete(kSet);
    }

    /**
     * 设置key的生命周期
     *
     * @param key
     * @param time
     * @param timeUnit
     */
    public void expireKey(String key, long time, TimeUnit timeUnit) {
        redisTemplate.expire(key, time, timeUnit);
    }

    /**
     * 指定key在指定的日期过期
     *
     * @param key
     * @param date
     */
    public void expireKeyAt(String key, Date date) {
        redisTemplate.expireAt(key, date);
    }

    /**
     * 查询key的生命周期
     *
     * @param key
     * @param timeUnit
     * @return
     */
    public long getKeyExpire(String key, TimeUnit timeUnit) {
        return redisTemplate.getExpire(key, timeUnit);
    }

    /**
     * 将key设置为永久有效
     *
     * @param key
     */
    public void persistKey(String key) {
        redisTemplate.persist(key);
    }
    
    /*** 集合操作*/
    
    
    /**
     * hmset命令
     * */
    public void hashPutAll(String key,Map map) {
    	hashOperations.putAll(key, map);
    }
    
    /**
     * 获取链表长度
     * */
    public Long getRedisListSize(String key) {
    	return listOperations.size(key);
    }
    
    /**
     * 删除一个集合的元素，可以是多个
     * */
    public Long removeSetElems(String key, Object...values) {
    	return setOperations.remove(key, values);
    }
    
    /**
     * 将元素插入有序集合
     * */
    public Long add2ZSet(String key, Set tuples) {
    	return zSetOperations.add(key, tuples);
    }
}
