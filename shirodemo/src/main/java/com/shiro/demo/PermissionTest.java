package com.shiro.demo;

import org.apache.shiro.authz.UnauthorizedException;
import org.junit.Test;

import junit.framework.Assert;

/**
 * @author AmVilCres
 * @desc  权限测试类
 * @date 2018年9月9日
 */
public class PermissionTest extends BaseTest{

	@Test
	public void testCheckPermission() {
		login("classpath:shiro-permission.ini","li","123");
		// 断言拥有权限：user:create
		//subject().checkPermission("user:create");
		// 断言拥有权限： user:delete and user:update
		//subject().checkPermissions("user:delete","user:update");
		// 断言拥有权限： user:view 失败抛出异常
		//subject().checkPermissions("user:view");
		subject().checkPermissions("system:user:create,delete,update:view");
		
		
	}
	
	@Test
	public void testIsPermitted() {
		login("classpath:shiro-permission.ini","zhang","123");
		// 判断拥有权限： user:create
		Assert.assertTrue(subject().isPermitted("user:create"));
		// 判断拥有权限： user:update  user:delete
		Assert.assertTrue(subject().isPermittedAll("user:update","user:delete"));
		// 判断没有权限： user:view
		Assert.assertFalse(subject().isPermitted("user:view"));
	}
	
	@Test
	public void testIsPermitted2() {
		login("classpath:shiro-authorizer.ini","zhang","123");
		// 判断拥有权限： user:create
		Assert.assertTrue(subject().isPermitted("user1:create"));
		Assert.assertTrue(subject().isPermitted("user2:update"));
		//通过二进制位的方式表示权限
		Assert.assertTrue(subject().isPermitted("+user1+2"));//新增权限
		Assert.assertTrue(subject().isPermitted("+user1+8"));//查看权限
		Assert.assertTrue(subject().isPermitted("+user2+10"));//新增及查看
		Assert.assertFalse(subject().isPermitted("+user1+4"));//没有删除权限
		Assert.assertTrue(subject().isPermitted("menu:view"));// 通 过MyRolePermissionResolver 解析得到的权限
	}
}
