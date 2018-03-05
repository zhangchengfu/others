package com.avc.rp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.avc.rp.dao.RedPacketDao;
import com.avc.rp.model.RedPacket;
import com.avc.rp.service.RedPacketService;

@Service
public class RedPacketServiceImpl implements RedPacketService {

	@Autowired
	private RedPacketDao redPacketMapper = null;;
	
	@Override
	@Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED)
	public RedPacket getRedPacket(Integer id) {
		return redPacketMapper.getRedPacket(id);
	}

	@Override
	@Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED)
	public int decreaseRedPacket(Integer id) {
		return redPacketMapper.decreaseRedPacket(id);
	}

}
