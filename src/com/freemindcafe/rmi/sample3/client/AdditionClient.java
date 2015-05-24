package com.freemindcafe.rmi.sample3.client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import com.freemindcafe.rmi.sample3.server.IAddition;

public class AdditionClient {
	
	public static void main(String[] args) throws Exception {
		IAddition hello;
		Registry registry = LocateRegistry.getRegistry(9999);
		hello = (IAddition)registry.lookup("rmi://localhost/rmi-sample3");
		int result=hello.add(9,10);
		System.out.println("Result is :"+result);
	}

}
