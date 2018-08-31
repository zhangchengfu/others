package com.example.demo.shiro.session.impl;

import java.io.Serializable;
import java.util.Collection;

import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.redis.RedisConstant;
import com.example.demo.redis.RedisOperator;
import com.example.demo.shiro.session.ShiroSessionRepository;

public class RedisShiroSessionRepository implements ShiroSessionRepository {

	@Autowired
	private RedisOperator redisOpt;
	
	@Override
	public void saveSession(Session session) {
		redisOpt.set(RedisConstant.SHIRO_LOGIN_SESSION, session, RedisConstant.NOT_EXPIRE);
	}

	@Override
	public void deleteSession(Serializable sessionId) {
		//redisOpt.deleteKey(sessionId);
	}

	@Override
	public Session getSession(Serializable sessionId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Session> getAllSessions() {
		// TODO Auto-generated method stub
		return null;
	}

}
