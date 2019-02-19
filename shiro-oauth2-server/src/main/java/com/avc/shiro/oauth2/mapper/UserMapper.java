package com.avc.shiro.oauth2.mapper;

import com.avc.shiro.oauth2.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    User selectByUserName(@Param("username")String username);
}
