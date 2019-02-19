package com.avc.shiro.oauth2.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages= {"com.avc.shiro.oauth2.mapper"})
public class MybtaisConfig {

}
