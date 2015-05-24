package com.freemindcafe.rmi.sample2.server;

import java.rmi.Naming;

public class AdditionServer {
	
	public static void main(String[] args) throws Exception{
		System.setSecurityManager(new SecurityManager());
		Addition Hello = new Addition();
		Naming.rebind("rmi://localhost/rmi-sample2", Hello);
		System.out.println("Addition Server is ready.");
	}

}
