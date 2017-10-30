package com.avc.proxy.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKProxyExample implements InvocationHandler {
	
	private Object target=null; //真是的对象

	/**
	 * 建立代理对象和真实对象的代理关系
	 * @param target 真实对象
	 * @return 代理对象
	 * */
	public Object bind(Object target) {
		this.target = target;
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
	}

	/**
	 * 代理方法逻辑
	 * @param proxy 代理对象
	 * @param method 当前调度的方法
	 * @param args 当前方法的参数
	 * @return 代理结果返回
	 * @throws Throwable 异常
	 * */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("进入代理逻辑方法");
		System.out.println("在调度真实对象之前的服务");
		Object obj = method.invoke(target, args); //相当于调用sayHello方法
		System.out.println("在调度真实对象之后的服务");
		return obj;
	}

}
