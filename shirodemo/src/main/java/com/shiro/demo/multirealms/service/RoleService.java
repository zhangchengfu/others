package com.shiro.demo.multirealms.service;

import com.shiro.demo.multirealms.entity.Role;

/**
 * @author AmVilCres
 * @desc 
 * @date 2018年10月9日
 */
public interface RoleService {
	public Role createRole(Role role);
	public void deleteRole(Long roleId);

	/**
	 * 添加角色-权限之间关系
	 * @param roleId
	 * @param permissionIds
	 */
	public void correlationPermissions(Long roleId, Long... permissionIds);

	/**
	 * 移除角色-权限之间关系
	 * @param roleId
	 * @param permissionIds
	 */
	public void uncorrelationPermissions(Long roleId, Long... permissionIds);
}
