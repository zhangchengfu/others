package com.example.demo.redis;

public class RedisConstant {
	 /**
     * 默认过期时长，单位：秒
     */
    public static final long DEFAULT_EXPIRE = 60 * 60 * 24;

    /**
     * 不设置过期时长
     */
    public static final long NOT_EXPIRE = -1;
    
    /**
     * 用户登录Session Key
     * */
	public static final String SHIRO_LOGIN_SESSION = "shiro_login_session";
}
