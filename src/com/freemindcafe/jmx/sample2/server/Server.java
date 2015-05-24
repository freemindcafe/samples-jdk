package com.freemindcafe.jmx.sample2.server;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.net.ServerSocket;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.Random;

import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;
import javax.management.remote.rmi.RMIConnectorServer;

import com.sun.jndi.rmi.registry.RegistryContextFactory;
 
public class Server {
	
	private static final Random random = new Random();
 
    public static void main(String[] args) 
        throws Exception { 
    	
    	int jmxPort = 1919;
    	LocateRegistry.createRegistry(jmxPort);
    	
    	//MBeanServer mBeanServer = MBeanServerFactory.newMBeanServer("ENERGYIPAPP");
    	MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
    	
    	// create JMXConnectorServer MBean    	
    	JMXServiceURL address = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:"+jmxPort+"/EIPRMIConnector");    	
        HashMap<String, String> environment = new HashMap<String, String>();
        environment.put("java.naming.factory.initial", RegistryContextFactory.class.getName());
        environment.put(RMIConnectorServer.JNDI_REBIND_ATTRIBUTE,"true");
        JMXConnectorServer connector = JMXConnectorServerFactory.newJMXConnectorServer(address, environment, mBeanServer);
        connector.start();
        
        ObjectName mbeanName = new ObjectName("com.eMeter.localhost.appName.appInstance:name=EIP.IHelloService"); 
    	HelloService helloService = new HelloService(); 
    	mBeanServer.registerMBean(helloService, mbeanName); 
    	
        System.out.println("Waiting forever..."); 
        Thread.sleep(Long.MAX_VALUE); 
    } 
    
	private static int findFreePort(int minPortNumber, int maxPortNumber) {
		if (minPortNumber >= maxPortNumber) {
			throw new RuntimeException("Invalid port number range min ["
					+ minPortNumber + "] max [" + maxPortNumber + "]");
		}
		for (int i = 0; i < 20; i++) {
			int port = createAcceptablePort(minPortNumber, maxPortNumber);
			if (checkPortIsFree(port)) {
				return port;
			}
		}
		throw new RuntimeException("Unable to find a free port");
	}

	private static int createAcceptablePort(int minPortNumer, int maxPortNumber) {
		synchronized (random) {
			int randomInt = random.nextInt();
			final int portWithoutOffset = Math.abs(randomInt
					% (maxPortNumber - minPortNumer + 1));
			return portWithoutOffset + minPortNumer;
		}

	}
	
	private static boolean checkPortIsFree(int port) {
		ServerSocket socket;
		try {
			socket = new ServerSocket(port);
			int localPort = socket.getLocalPort();
			socket.close();
			return true;
		} catch (IOException e) {
			return false;
		}
	}	
} 
