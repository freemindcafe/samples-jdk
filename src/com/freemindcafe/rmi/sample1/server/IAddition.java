package com.freemindcafe.rmi.sample1.server;

import java.rmi.RemoteException;

public interface IAddition extends java.rmi.Remote {
	
	public int add(int a,int b) throws RemoteException;

}
