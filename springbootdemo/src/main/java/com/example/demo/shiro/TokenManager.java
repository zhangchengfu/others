package com.example.demo.shiro;

import org.apache.shiro.SecurityUtils;

import com.example.demo.model.User;

public class TokenManager {
	
	/**
	 * 获取当前登录的对象
	 * */
	public static User getToken() {
		return (User)SecurityUtils.getSubject().getPrincipal();
	}
	
	/**
	 * 登录
	 * */
	public static User login(User user,Boolean rememberMe){
		ShiroToken token = new ShiroToken(user.getEmail(), user.getPswd());
		token.setRememberMe(rememberMe);
		SecurityUtils.getSubject().login(token);		
		return getToken();
	}
	
	/**
	 * 判断时候登录
	 * */
	public static boolean isLogin() {
		return null != SecurityUtils.getSubject().getPrincipal();
	}
	
	/**
	 * 退出登录
	 * */
	public static void logout() {
		SecurityUtils.getSubject().logout();
	}
}
