package com.avc.interceptor;

import java.lang.reflect.Method;

/**
 * @author AmVilCres
 * 
 * <p>
 * 	该接口定义了3个方法：before、around、after
 * 	<li>3个方法的参数为：proxy代理对象、target真实的对象、method方法、args运行方法参数</li>
 * 	<li>before方法返回boolean值，它在真实对象前调用。当返回true时，则反射真实对象的方法；当返回false时，则调用around方法</li>
 * 	<li>在反射真实对象方法或者around方法执行之后，调用after方法</li>
 * </p>
 */
public interface Interceptor {
	public boolean before(Object proxy, Object target, Method method, Object[] args);
	public void around(Object proxy, Object target, Method method, Object[] args);
	public void after(Object proxy, Object target, Method method, Object[] args);
}
