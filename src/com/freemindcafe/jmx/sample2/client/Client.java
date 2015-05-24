package com.freemindcafe.jmx.sample2.client;

public class Client {
	
	public static void main(String[] args) {
		HelloServiceImpl helloServiceImpl = new HelloServiceImpl();
		helloServiceImpl.sayHello();
	}

}
