/**
 * 
 */
package com.avc.interceptor.link;

import com.avc.interceptor.InterceptorJdkProxy;
import com.avc.proxy.jdkproxy.HelloWorld;
import com.avc.proxy.jdkproxy.HelloWorldImpl;

/**
 * @author AmVilCres
 * @desc 
 * @date 2017Äê10ÔÂ30ÈÕ
 */
public class LinkInterceptorTest {
	
	public static void main(String[] args) {
		HelloWorld proxy1 = (HelloWorld) InterceptorJdkProxy.bind(new HelloWorldImpl(), "com.avc.interceptor.link.Interceptor1");
		HelloWorld proxy2 = (HelloWorld) InterceptorJdkProxy.bind(proxy1, "com.avc.interceptor.link.Interceptor2");
		HelloWorld proxy3 = (HelloWorld) InterceptorJdkProxy.bind(proxy2, "com.avc.interceptor.link.Interceptor3");
		proxy3.sayHello();
	}
}
