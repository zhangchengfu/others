package com.shiro.demo.permission;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.PermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermission;

/**
 * @author AmVilCres
 * @desc 
 * @date 2018年9月11日
 */
public class BitAndWildPermissionResolver implements PermissionResolver{

	@Override
	public Permission resolvePermission(String permissionString) {
		if(permissionString.startsWith("+")) {
			return new BitPermission(permissionString);
		}
		return new WildcardPermission(permissionString);
	}

}
