package com.shiro.demo.multirealms.service.impl;

import com.shiro.demo.multirealms.dao.PermissionDao;
import com.shiro.demo.multirealms.dao.impl.PermissionDaoImpl;
import com.shiro.demo.multirealms.entity.Permission;
import com.shiro.demo.multirealms.service.PermissionService;

/**
 * @author AmVilCres
 * @desc 
 * @date 2018年10月7日
 */
public class PermissionServiceImpl implements PermissionService {
	
	private PermissionDao permissionDao = new PermissionDaoImpl();

	@Override
	public Permission createPermission(Permission permission) {
		return permissionDao.createPermission(permission);
	}

	@Override
	public void deletePermission(Long permissionId) {
		permissionDao.deletePermission(permissionId);
	}

}
