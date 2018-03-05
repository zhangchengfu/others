package com.avc.rp.config;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	//Spring Ioc环境配置
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] {RootConfig.class};
	}

	//DispatcherServlet环境配置
	@Override
	protected Class<?>[] getServletConfigClasses() {
		//加载java配置类
		return new Class<?>[] {WebConfig.class};
	}

	//DispatcherSelver拦截请求配置
	@Override
	protected String[] getServletMappings() {
		return new String[] {"*.do"};
	}
	
	/**
	 * @param dynamic Servlet上传文件配置
	 * */
	protected void customizeRegistration(Dynamic dynamic) {
		//配置上传路径
		String filePath = "e:/redPacket";
		//5MB
		Long singleMax = (long)(5*Math.pow(2, 20)); 
		//10MB
		Long totleleMax = (long)(10*Math.pow(2, 20)); 
		//设置上传文件配置
		dynamic.setMultipartConfig(new MultipartConfigElement(filePath, singleMax, totleleMax, 0));
	}
}
