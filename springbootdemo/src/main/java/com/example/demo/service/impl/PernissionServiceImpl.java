package com.example.demo.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.PermissionMapper;
import com.example.demo.service.PermissionSerivce;

@Service
public class PernissionServiceImpl implements PermissionSerivce{
	
	@Autowired
	private PermissionMapper permissionMapper;
	
	@Override
	public Set<String> findPermissionByUserId(Long userId) {
		return permissionMapper.findPermissionByUserId(userId);
	}

}
