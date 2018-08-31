package com.example.demo.service;

import com.example.demo.model.User;

public interface UserService {
	User findUserById(Integer id);
	User login(String email, String password);
	int updateByPrimaryKeySelective(User user);
}
