package com.avc.reflect;

import java.lang.reflect.Method;

public class ReflectServiceImpl2 {
	private String name;
	
	public String getName() {
		return name;
	}

	public ReflectServiceImpl2(String name) {
		this.name = name;
	}

	public void sayHello(String name) {
		System.out.println("Hello"+name);
	}
	
	public static ReflectServiceImpl2 getInstance() {
		ReflectServiceImpl2 obj = null;
		try {
			obj = (ReflectServiceImpl2)Class.forName("com.avc.reflect.ReflectServiceImpl2").getConstructor(String.class).newInstance("Å£Æ¤");
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return obj;
	}
	
	public Object ReflectMethod() {
		ReflectServiceImpl2 obj = ReflectServiceImpl2.getInstance();
		Method method;
		try {
			method = obj.getClass().getMethod("sayHello", String.class);
			method.invoke(obj, "ÉßÆ¤");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return obj;
	}
}
