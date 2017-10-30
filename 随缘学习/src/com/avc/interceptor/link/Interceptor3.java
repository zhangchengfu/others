package com.avc.interceptor.link;

import java.lang.reflect.Method;

import com.avc.interceptor.Interceptor;

/**
 * @author AmVilCres
 * @desc 
 * @date 2017年10月30日
 */
public class Interceptor3 implements Interceptor {

	@Override
	public boolean before(Object proxy, Object target, Method method, Object[] args) {
		System.out.println("拦截器  3 的 before方法");
		return true;
	}

	@Override
	public void around(Object proxy, Object target, Method method, Object[] args) {
		System.out.println("拦截器  3 的 around方法");
	}

	@Override
	public void after(Object proxy, Object target, Method method, Object[] args) {
		System.out.println("拦截器  3 的 after方法");
	}

}
