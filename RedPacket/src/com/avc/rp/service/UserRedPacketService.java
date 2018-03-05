package com.avc.rp.service;

public interface UserRedPacketService {
	 /**
     * 保存抢红包信息
     * @param redPacketId 红包ID
     * @param userId 抢红包用户编好
     * @return 影响的记录数
     * */
    public int grapRedPacket(Integer redPacketId,Integer userId);
    
    public int grapRedPacketForVersion(Integer redPacketId, Integer userId);
    
    /**
     * Redis实现抢红包
     * @param redPacketId 红包ID
     * @param userId 抢红包用户编号
     * @return 
     * 0 -没有库存，失败
     * 1 -成功，且不是最后一个红包
     * 2 -成功，且是最后一个红包
     * */
    public Long grapRedPacketByRedis(Integer redPacketId, Integer userId);
}
