package com.shiro.demo;

import java.util.Arrays;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.authz.ModularRealmAuthorizer;
import org.apache.shiro.authz.permission.WildcardPermissionResolver;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

import com.alibaba.druid.pool.DruidDataSource;

import junit.framework.Assert;

/**
 * @author AmVilCres
 * @desc 
 * @date 2018年9月12日
 */
public class NonConfigurationCreateTest {
	
	@Test
	public void test() {
		DefaultSecurityManager securityManager = new DefaultSecurityManager();
		
		// 设置authenticator
		ModularRealmAuthenticator authenticator = new ModularRealmAuthenticator();
		authenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());
		securityManager.setAuthenticator(authenticator);
		
		// 设置 authorizer
		ModularRealmAuthorizer authorizer = new ModularRealmAuthorizer();
		authorizer.setPermissionResolver(new WildcardPermissionResolver());
		securityManager.setAuthorizer(authorizer);
		
		// 设置 Relam
		
		DruidDataSource ds = new DruidDataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/shiro");
		ds.setPassword("root");
		ds.setUsername("root");
		JdbcRealm jdbcRealm = new JdbcRealm();
		jdbcRealm.setDataSource(ds);
		jdbcRealm.setPermissionsLookupEnabled(true);
		securityManager.setRealms(Arrays.asList((Realm)jdbcRealm));
		
		// 将 SecurityManager 设置到 SecurityUtils 方便全局使用
		SecurityUtils.setSecurityManager(securityManager);
		
		Subject subject = SecurityUtils.getSubject();
		
		UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
		subject.login(token);
		Assert.assertTrue(subject.isAuthenticated());
	}
	
}
