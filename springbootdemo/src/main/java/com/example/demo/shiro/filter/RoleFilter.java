package com.example.demo.shiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;


public class RoleFilter extends AccessControlFilter {

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		String[] arra = (String[])mappedValue;		
		Subject subject = getSubject(request, response);
		for (String role : arra) {
			if(subject.hasRole(role)){
				return true;
			}
		}
		return false;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		Subject subject = getSubject(request, response);
		//表示没有登录
		if(null == subject.getPrincipal()) {
			saveRequest(request);
			WebUtils.issueRedirect(request, response, "/g/unLogin");  
		}else {  
            if (StringUtils.hasText("/g/unauthorized")) {//如果有未授权页面跳转过去  
                WebUtils.issueRedirect(request, response, "/g/unauthorized");  
            } else {//否则返回401未授权状态码  
                WebUtils.toHttp(response).sendError(HttpServletResponse.SC_UNAUTHORIZED);  
            }  
        }  
		return false;
	}

}
