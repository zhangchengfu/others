package com.avc.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectServiceImpl {
	public void sayHello(String name) {
		System.out.println("Hello"+name);
	}
	
	public static ReflectServiceImpl getInstance() {
		ReflectServiceImpl obj = null;
		try {
			obj = (ReflectServiceImpl)Class.forName("com.avc.reflect.ReflectServiceImpl").newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return obj;
	}
	
	public Object reflectMethod() {
		Object obj = null;
		ReflectServiceImpl rsi = new ReflectServiceImpl();
		
		try {
			Method method = ReflectServiceImpl.class.getMethod("sayHello", String.class);
			method.invoke(rsi, "ÀîËÄ");
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return obj;
	}
}
