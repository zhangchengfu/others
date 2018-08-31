package com.example.demo.mapper;

import org.apache.ibatis.annotations.Param;

import com.example.demo.model.User;

public interface UserMapper {
	User getUserById(Integer id);

	User login(@Param("email")String email, @Param("password")String password);

	int updateByPrimaryKeySelective(User user);
}
