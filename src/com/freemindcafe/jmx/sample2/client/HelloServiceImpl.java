package com.freemindcafe.jmx.sample2.client;

import javax.management.MBeanServerConnection;
import javax.management.MBeanServerInvocationHandler;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import com.freemindcafe.jmx.sample1.server.IHelloService;

public class HelloServiceImpl implements IHelloService {
	
	IHelloService helloService;
	
	public HelloServiceImpl(){
		String jmxPort = "1919";
		String hostName = "localhost";
		String mbean = "com.eMeter.localhost.appName.appInstance:name=EIP.IHelloService";
		
		JMXServiceURL url;
		try {
			url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:"+jmxPort+"/EIPRMIConnector");
			JMXConnector jmxc = JMXConnectorFactory.connect(url);
			MBeanServerConnection conn = jmxc.getMBeanServerConnection();
			ObjectName objectName = new ObjectName(mbean);
			//ObjectName activeMQ = new ObjectName("org.apache.activemq:BrokerName=localhost,Type=Broker");
			helloService = (IHelloService) MBeanServerInvocationHandler
					.newProxyInstance(conn, objectName,
							IHelloService.class, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

	@Override
	public void sayHello() {
		helloService.sayHello();		
	}

	@Override
	public int add(int x, int y) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getCacheSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setCacheSize(int size) {
		// TODO Auto-generated method stub
		
	}

}
