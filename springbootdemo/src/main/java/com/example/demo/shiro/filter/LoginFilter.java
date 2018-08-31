package com.example.demo.shiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.filter.AccessControlFilter;

import com.example.demo.model.User;
import com.example.demo.shiro.TokenManager;

/**
 * 判断登录
 * */
public class LoginFilter extends AccessControlFilter {

	 //是否允许访问，如果允许访问返回true，否则false
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		User user = TokenManager.getToken();
		/*		String url = getPathWithinApplication(request);
		Subject subject = getSubject(request,response);*/
		System.out.println("进入LoginFilter");
		if(user != null || isLoginRequest(request, response)) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		//保存Request和Response 到登录后的链接
		saveRequestAndRedirectToLogin(request,response);
		return false;
	}

}
