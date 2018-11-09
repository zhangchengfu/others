package com.shiro.demo.multirealms.service;

import com.shiro.demo.multirealms.entity.Permission;

/**
 * @author AmVilCres
 * @desc 
 * @date 2018年10月7日
 */
public interface PermissionService {
	public Permission createPermission(Permission permission);
	public void deletePermission(Long permissionId);
}
