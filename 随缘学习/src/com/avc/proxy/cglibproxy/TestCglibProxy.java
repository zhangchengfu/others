package com.avc.proxy.cglibproxy;

import com.avc.reflect.ReflectServiceImpl;

public class TestCglibProxy {
	public static void main(String[] args) {
		CglibProxyExample cpe = new CglibProxyExample();
		ReflectServiceImpl rsi = (ReflectServiceImpl)cpe.getProxy(ReflectServiceImpl.class);
		rsi.sayHello("ţƤ");
	}
}
