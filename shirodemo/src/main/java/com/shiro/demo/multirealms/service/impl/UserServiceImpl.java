package com.shiro.demo.multirealms.service.impl;

import java.util.Set;

import com.shiro.demo.multirealms.dao.UserDao;
import com.shiro.demo.multirealms.dao.impl.UserDaoImpl;
import com.shiro.demo.multirealms.entity.User;
import com.shiro.demo.multirealms.service.PasswordHelper;
import com.shiro.demo.multirealms.service.UserService;

/**
 * @author AmVilCres
 * @desc 
 * @date 2018年10月9日
 */
public class UserServiceImpl implements UserService {
	
	 private UserDao userDao = new UserDaoImpl();
	 private PasswordHelper passwordHelper = new PasswordHelper();

	@Override
	public User createUser(User user) {
		//加密密码
		passwordHelper.encryptPassword(user);
		return userDao.createUser(user);
	}

	/**
	 * 修改密码
	 * */
	@Override
	public void changePassword(Long userId, String newPassword) {
		User user = userDao.findOne(userId);
		user.setPassword(newPassword);
		passwordHelper.encryptPassword(user);
		userDao.updateUser(user);
	}

	/**
	 * 添加用户-角色关系
	 * @param userId
	 * @param roleIds
	 * */
	@Override
	public void correlationRoles(Long userId, Long... roleIds) {
		userDao.correlationRoles(userId, roleIds);
	}

	/**
	 * 移除用户-角色关系
	 * @param userId
	 * @param roleIds
	 * */
	@Override
	public void uncorrelationRoles(Long userId, Long... roleIds) {
		userDao.uncorrelationRoles(userId, roleIds);
	}

	/**
	 * 根据用户名查找用户
	 * @param username
	 * */
	@Override
	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}

	/**
     * 根据用户名查找其角色
     * @param username
     * @return
     */
	@Override
	public Set<String> findRoles(String username) {
		return userDao.findRoles(username);
	}

	/**
     * 根据用户名查找其权限
     * @param username
     * @return
     */
	@Override
	public Set<String> findPermissions(String username) {
		return userDao.findPermissions(username);
	}

}
