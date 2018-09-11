package com.shiro.demo.permission;

import java.util.Arrays;
import java.util.Collection;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.RolePermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermission;

/**
 * @author AmVilCres
 * @desc 
 * @date 2018年9月11日
 */
public class MyRolePermissionResolver implements RolePermissionResolver{

	@Override
	public Collection<Permission> resolvePermissionsInRole(String roleString) {
		if("role1".equals(roleString)) {
			return Arrays.asList((Permission)new WildcardPermission("menu:*"));
		}
		return null;
	}

}
