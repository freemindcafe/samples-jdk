package com.freemindcafe.rmi.sample1.client;

import java.rmi.Naming;

import com.freemindcafe.rmi.sample1.server.IAddition;

public class AdditionClient {
	
	public static void main(String[] args) throws Exception {
		IAddition hello;
		hello = (IAddition)Naming.lookup("rmi://localhost/rmi-sample1");
		int result=hello.add(9,10);
		System.out.println("Result is :"+result);
	}

}
