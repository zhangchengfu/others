package com.avc.shiro.oauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class ShiroOAuth2ServerApplication extends SpringBootServletInitializer{

    public static void main(String[] args) {
        SpringApplication.run(ShiroOAuth2ServerApplication.class,args);
    }
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ShiroOAuth2ServerApplication.class);
    }
}
