package com.avc.shiro.oauth2.mapper;

import com.avc.shiro.oauth2.entity.Client;
import org.apache.ibatis.annotations.Param;

public interface ClientMapper {

    Client selectByClientId(@Param("clientId") String clientId);

    Client selectBySecret(@Param("clientSecret")String clientSecret);
}
