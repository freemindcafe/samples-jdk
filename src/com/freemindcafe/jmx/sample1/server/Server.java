package com.freemindcafe.jmx.sample1.server;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;
import javax.management.ObjectName;
 
public class Server {
	
    public static void main(String[] args) 
        throws Exception { 
    	
    	MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
    	
        ObjectName mbeanName = new ObjectName("com.eMeter.localhost.appName.appInstance:name=EIP.IHelloService"); 
    	HelloService helloService = new HelloService(); 
    	mBeanServer.registerMBean(helloService, mbeanName); 
    	
        System.out.println("Waiting forever..."); 
        Thread.sleep(Long.MAX_VALUE); 
    }     

} 
