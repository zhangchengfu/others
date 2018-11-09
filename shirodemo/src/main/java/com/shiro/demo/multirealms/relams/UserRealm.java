package com.shiro.demo.multirealms.relams;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.*;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import com.shiro.demo.multirealms.entity.User;
import com.shiro.demo.multirealms.service.UserService;
import com.shiro.demo.multirealms.service.impl.UserServiceImpl;

/**
 * @author AmVilCres
 * @desc 
 * @date 2018年10月17日
 */
public class UserRealm extends AuthorizingRealm{
	
	private UserService userService = new UserServiceImpl();

	/**
	 * 授权
	 * */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String username = (String)principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(userService.findRoles(username));
        authorizationInfo.setStringPermissions(userService.findPermissions(username));
		return authorizationInfo;
	}

	/**
	 * 身份认证
	 * */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = (String) token.getPrincipal();
		User u = userService.findByUsername(username);
		if(null == u) {
			throw new UnknownAccountException("该用户不存在");//没找到帐号
		}
		if(Boolean.TRUE.equals(u.getLocked())) {
			throw new LockedAccountException("该账户已被锁定"); //帐号锁定
		}
		//交给 AuthenticatingRealm 使用 CredentialsMatcher 进行密码匹配，如果觉得的不好可以在此判断或自定义实现
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
						u.getUsername(), //用户名
						u.getPassword(), //密码
						ByteSource.Util.bytes(u.getCredentialsSalt()),//salt=username+salt
						getName() //realm name
		);
		return authenticationInfo;
	}

}
