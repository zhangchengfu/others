package com.shiro.demo.multirealms.service.impl;

import com.shiro.demo.multirealms.dao.RoleDao;
import com.shiro.demo.multirealms.dao.impl.RoleDaoImpl;
import com.shiro.demo.multirealms.entity.Role;
import com.shiro.demo.multirealms.service.RoleService;

/**
 * @author AmVilCres
 * @desc 
 * @date 2018年10月9日
 */
public class RoleServiceImpl implements RoleService {

	private RoleDao roleDao = new RoleDaoImpl();

	@Override
	public Role createRole(Role role) {
		return roleDao.createRole(role);
	}

	@Override
	public void deleteRole(Long roleId) {
		roleDao.deleteRole(roleId);
	}

	/**
     * 添加角色-权限之间关系
     * @param roleId
     * @param permissionIds
     */
	@Override
	public void correlationPermissions(Long roleId, Long... permissionIds) {
		roleDao.correlationPermissions(roleId, permissionIds);
	}

	/**
     * 移除角色-权限之间关系
     * @param roleId
     * @param permissionIds
     */
	@Override
	public void uncorrelationPermissions(Long roleId, Long... permissionIds) {
		 roleDao.uncorrelationPermissions(roleId, permissionIds);
	}

}
