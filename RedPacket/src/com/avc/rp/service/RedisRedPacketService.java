package com.avc.rp.service;

public interface RedisRedPacketService {
	/**
	 * 保存redis抢红包列表
	 * @param redPacketId   --红包编号
	 * @param unitAmount --红包金额
	 * */
	public void saveUserRedPacketByRedis(Integer redPacketId, Double unitAmount);
}
