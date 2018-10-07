package com.shiro.demo;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

import junit.framework.Assert;

/**
 * @author AmVilCres
 * @desc 
 * @date 2018年9月12日
 */
public class ConfigurationCreateTest {

	@Test
	public void test() {
		Factory<org.apache.shiro.mgt.SecurityManager> factory = 
				new IniSecurityManagerFactory("classpath:shiro-config-main.ini");
		org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
		// 将 SecurityManager 设置到SecurityUtils 方便全局使用
		SecurityUtils.setSecurityManager(securityManager);
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
		subject.login(token);
		Assert.assertTrue(subject.isAuthenticated());
	}
}
