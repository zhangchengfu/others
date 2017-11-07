package com.avc.proxy.cglibproxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * @desc  CGLIB代理不需要提供接口，只要一个非抽象类就能实现代理
 * */
public class CglibProxyExample implements MethodInterceptor{

	/**
	 * 生成CGLIB代理对象
	 * @param cls  Class类
	 * @return Class类的CGLIB代理对象
	 * */
	public Object getProxy(Class cls) {
		//CGLIB enhancer 增强类对象
		Enhancer enhancer = new Enhancer();
		
		//设置增强类型
		enhancer.setSuperclass(cls);
		
		//定义代理逻辑对象为当前对象，要求当前对象实现MethodInterceptor
		enhancer.setCallback(this);
		return enhancer.create();
	}
	
	@Override
	public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
		System.out.println("调用真实对象之前");
		//CGLIB反射调用真实对象的方法
		Object res = methodProxy.invokeSuper(proxy, args);
		System.out.println("调用真实对象之后");
		return res;
	}
	
	
}
