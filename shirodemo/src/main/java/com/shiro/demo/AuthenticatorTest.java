package com.shiro.demo;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

import junit.framework.Assert;

/**
 * @author AmVilCres
 * @desc 成功策略测试类
 * @date 2018年9月9日
 */
public class AuthenticatorTest {
	
	/**
	 * 只允许有一个Realm验证成功
	 * */
	@Test
	public void testOnlyOneSuccessfulStrategyWithSuccess() {
		login("classpath:shiro-authenticator-onlyone-success.ini");
	}
	
	/**
	 * 至少有两个Realm验证成功（自定义）
	 * */
	@Test
	public void testAtLeastTwoSuccessFulStrategyWithSuccess() {
		login("classpath:shiro-authenticator-atLeastTwo-success.ini");
	}
	
	/**
	 * 返回第一个成功的验证信息
	 * */
	@Test
	public void testFirstOneSuccessfulStrategyWithSuccess() {
		login("classpath:shiro-authenticator-first-success.ini");
	}
	
	/**
	 * 至少有一个验证成功
	 * */
	@Test
	public void testAtLeastOneSuccessfulStrategyWithSuccess() {
		login("classpath:shiro-authenticator-atLeastOne-success.ini");
	}
	
	/**
	 * 所有的Realm验证成功
	 * */
	@Test
	public void testAllSuccessfulStrategyWithSuccess() {
		login("classpath:shiro-authenticator-all-success.ini");
		Subject subject = SecurityUtils.getSubject();
		
		//得到一个身份集合，其包含了Realm验证成功的身份信息
		PrincipalCollection principalCollection =subject.getPrincipals();
		//Assert.assertEquals(2, principalCollection.asList().size());
	}
	
	// 登录逻辑
	private void login(String configFile) {
		Factory<SecurityManager> factory = new IniSecurityManagerFactory(configFile);
		
		SecurityManager securityManager = factory.getInstance();
		
		SecurityUtils.setSecurityManager(securityManager);
		
		Subject subject = SecurityUtils.getSubject();
		
		UsernamePasswordToken token = new UsernamePasswordToken("zhang","123");
		
		subject.login(token);
	}
}
