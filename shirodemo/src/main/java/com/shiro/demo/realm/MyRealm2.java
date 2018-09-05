package com.shiro.demo.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

public class MyRealm2 implements Realm {
    public String getName() {
        return "myrealm2";
    }

    public boolean supports(AuthenticationToken authenticationToken) {
        // 仅支持UsernamePasswordToken类型的Token
        return authenticationToken instanceof UsernamePasswordToken;
    }

    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();//得到用户名
        String password = new String((char[])authenticationToken.getCredentials()); // 得到密码
        if(!"wang".equals(username)){
            throw new UnknownAccountException("该账户不存在");
        }

        if(!"123".equals(password)){
            throw new IncorrectCredentialsException("密码不正确");
        }
        // 如果身份认证成功，返回一个AuthenticationInfo 实现
        return new SimpleAuthenticationInfo(username,password,getName());
    }
}
