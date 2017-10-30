package com.avc.interceptor;

import com.avc.proxy.jdkproxy.HelloWorld;
import com.avc.proxy.jdkproxy.HelloWorldImpl;

/**
 * @author AmVilCres
 * @desc 
 * @date 2017Äê10ÔÂ30ÈÕ
 */
public class TestInterceptorJdkProxy {
	public static void main(String[] args) {
		HelloWorld proxy = (HelloWorld) InterceptorJdkProxy.bind(new HelloWorldImpl(), "com.avc.interceptor.MyInterceptor");
		proxy.sayHello();
	}
}
