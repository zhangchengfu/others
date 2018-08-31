package com.example.demo.shiro;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.mgt.SecurityManager;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.exception.RepateLoginException;
import com.example.demo.model.User;
import com.example.demo.redis.RedisConstant;
import com.example.demo.redis.RedisOperator;
import com.example.demo.service.PermissionSerivce;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
import com.example.demo.utils.MD5;

public class UserAuthcRealm extends AuthorizingRealm{

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private PermissionSerivce permissionService;
	
	@Autowired
	private RedisOperator redisOpt;
	
	/**
	 * 认证
	 * */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException{
		ShiroToken token = (ShiroToken) authcToken;
		User account = userService.login(token.getUsername(), MD5.digest(token.getPswd()));
		
		if(account == null) {
			throw new AccountException("用户名或密码不正确");
		}
		if(account.getStatus() == User.STATUS_0) {
			throw new DisabledAccountException("该账户已被禁用");
		}
		account.setLastLoginTime(new Date());
		userService.updateByPrimaryKeySelective(account);
		/*System.err.println(RedisConstant.SHIRO_LOGIN_SESSION+account.getId());
		if(redisOpt.exists(RedisConstant.SHIRO_LOGIN_SESSION+account.getId())) {
			throw new RepateLoginException("账号已在别处登录");
		}
		redisOpt.set(RedisConstant.SHIRO_LOGIN_SESSION+account.getId(), account, -1L);*/
		return new SimpleAuthenticationInfo(account,account.getPswd(),getName());
	}
	
	/**
	 * 授权
	 * */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		System.out.println("开始进行用户授权");
		SimpleAuthorizationInfo info =  new SimpleAuthorizationInfo();
		User user = (User) principals.getPrimaryPrincipal();
		//根据用户ID查询所拥有的角色
		Set<String> roles = roleService.findRoleByUserId(user.getId());
		for (String string : roles) {
			System.out.println("role---"+string);
		}
		info.setRoles(roles);
		//根据用户ID查询权限（permission），放入到Authorization里。
		Set<String> permissions = permissionService.findPermissionByUserId(user.getId());
		info.setStringPermissions(permissions);
		return info;
	}

}
