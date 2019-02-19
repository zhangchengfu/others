package com.avc.shiro.oauth2.service.impl;

import com.avc.shiro.oauth2.entity.User;
import com.avc.shiro.oauth2.mapper.UserMapper;
import com.avc.shiro.oauth2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByUsername(String username) {
        return userMapper.selectByUserName(username);
    }
}
