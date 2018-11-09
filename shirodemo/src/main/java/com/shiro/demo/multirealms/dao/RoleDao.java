package com.shiro.demo.multirealms.dao;

import com.shiro.demo.multirealms.entity.Role;

/**
 * @author AmVilCres
 * @desc 
 * @date 2018年10月7日
 */
public interface RoleDao {
	public Role createRole(Role role);
    public void deleteRole(Long roleId);

    public void correlationPermissions(Long roleId, Long... permissionIds);
    public void uncorrelationPermissions(Long roleId, Long... permissionIds);

}
