package com.avc.proxy.jdkproxy;

public class TestJdkProxy {
	public static void main(String[] args) {
		JDKProxyExample jdk = new JDKProxyExample();
		
		//绑定关系，因为挂在接口HelloWorld下，所以声明代理对象HelloWord proxy
		HelloWorld proxy = (HelloWorld)jdk.bind(new HelloWorldImpl());
		
		//此时HelloWorld对象已经是一个代理对象，它会进入代理的逻辑方法invoke里面
		proxy.sayHello();
	}
}
