package com.avc.rp.service;

import com.avc.rp.model.RedPacket;

public interface RedPacketService {
    /**
     * 获取红包信息
     * @param id 红包 id
     * @return 红包具体信息
     * */
    public RedPacket getRedPacket(Integer id);
    
    /**
     * 扣减抢红包数
     * @param id  红包id
     * @return 更新记录条数
     * */
    public int decreaseRedPacket(Integer id);
}
