package com.freemindcafe.rmi.sample1.server;

import java.rmi.Naming;

public class AdditionServer {
	
	public static void main(String[] args) throws Exception{
		Addition Hello = new Addition();
		Naming.rebind("rmi://localhost/rmi-sample1", Hello);
		System.out.println("Addition Server is ready.");
	}

}
