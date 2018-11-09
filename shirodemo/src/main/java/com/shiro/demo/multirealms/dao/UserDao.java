package com.shiro.demo.multirealms.dao;

import java.util.Set;

import com.shiro.demo.multirealms.entity.User;

/**
 * @author AmVilCres
 * @desc 
 * @date 2018年10月9日
 */
public interface UserDao {
	public User createUser(User user);
	public void updateUser(User user);
	public void deleteUser(Long userId);

	public void correlationRoles(Long userId, Long... roleIds);
	public void uncorrelationRoles(Long userId, Long... roleIds);

	User findOne(Long userId);

	User findByUsername(String username);

	Set<String> findRoles(String username);

	Set<String> findPermissions(String username);
}
