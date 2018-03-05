package com.avc.rp.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.avc.rp.model.RedPacket;

@Repository
public interface RedPacketDao {

	/**
	 * 获取红包信息
	 * @param id 红包id
	 * @return 红包具体信息
	 */
	public RedPacket getRedPacket(Integer id);

	/**
	 * 扣减抢红包数
	 * 
	 * @param id
	 * @return 更新记录条数
	 */
	public int decreaseRedPacket(Integer id);
	
	public int decreaseRedPacketForVersion(@Param("id") Integer id, @Param("version") Integer version);
	
	/**
	 * 使用for update语句加锁
	 * @param id 红包id
	 * */
	public RedPacket getRedPacketForUpdate(Integer id);
	
}