package com.avc.interceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author AmVilCres
 * @desc 
 * @date 2017年10月30日
 */
public class InterceptorJdkProxy implements InvocationHandler{
	private Object target; //真实对象
	private String interceptorClass; // 拦截器全限定名
	
	public InterceptorJdkProxy(Object target,String interceptorClass) {
		this.target = target;
		this.interceptorClass = interceptorClass;
	}
	
	
	/**
	 * 绑定委托对象并返回一个 【代理占位】
	 * 
	 * @param target 真实的对象
	 * @return 代理对象【占位】
	 * */
	public static Object bind(Object target, String interceptorClass) {
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), 
				target.getClass().getInterfaces(), new InterceptorJdkProxy(target, interceptorClass));
	}
	
	
	/**
	 * 通过代理对象调用方法，首先进入的是这个方法
	 * 
	 * @param proxy 代理对象
	 * @param method  被调用的方法
	 * @param args 方法的参数
	 * */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		if(interceptorClass == null)
			return method.invoke(target, args);
		Object res = null;
		
		//通过反射生成拦截器
		Interceptor interceptor = (Interceptor) Class.forName(interceptorClass).newInstance();
		
		if(interceptor.before(proxy, target, method, args))
			res = method.invoke(target, args); //反射原有对象方法
		else {
			interceptor.around(proxy, target, method, args);
		}
		interceptor.after(proxy, target, method, args);
		
		return res;
	}
}
