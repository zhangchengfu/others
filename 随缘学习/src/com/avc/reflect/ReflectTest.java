package com.avc.reflect;

public class ReflectTest {
	
	public static void main(String[] args) {
		/*ReflectServiceImpl rsi= ReflectServiceImpl.getInstance();
		System.out.println(rsi.getClass().getSimpleName());
		rsi.reflectMethod();
		rsi.sayHello("ÉßÆ¤");*/
		ReflectServiceImpl2 rsi = ReflectServiceImpl2.getInstance();
		//System.out.println(rsi.getName());
		rsi.ReflectMethod();
	}
}
