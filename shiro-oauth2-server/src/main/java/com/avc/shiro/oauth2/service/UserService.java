package com.avc.shiro.oauth2.service;

import com.avc.shiro.oauth2.entity.User;

public interface UserService {
    User findByUsername(String username);
}
