package com.freemindcafe.rmi.sample3.server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class AdditionServer {
	
	public static void main(String[] args) throws Exception{
		Addition Hello = new Addition();
		Registry registry;
		try {
		    registry=LocateRegistry.getRegistry(9999);
		    registry.list();
		  }
		 catch (Exception e) {
		    registry=LocateRegistry.createRegistry(9999);
		    registry.list();
		  }		
		registry.bind("rmi://localhost/rmi-sample3", Hello);
		System.out.println("Addition Server is ready.");
	}

}
