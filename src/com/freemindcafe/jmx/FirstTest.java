package com.freemindcafe.jmx;

import javax.management.MBeanServerConnection;
import javax.management.MBeanServerInvocationHandler;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import org.apache.activemq.broker.jmx.BrokerViewMBean;
import org.apache.activemq.broker.jmx.QueueViewMBean;
import org.junit.Test;

public class FirstTest {
	
	@Test
	public void connect_to_activemq_mbean_objects() throws Exception{
		JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://nikhil-vm.emeter.com:1099/jmxrmi");
		JMXConnector jmxc = JMXConnectorFactory.connect(url);
		MBeanServerConnection conn = jmxc.getMBeanServerConnection();
		ObjectName activeMQ = new ObjectName("org.apache.activemq:type=Broker,brokerName=localhost");
		//ObjectName activeMQ = new ObjectName("org.apache.activemq:BrokerName=localhost,Type=Broker");
		BrokerViewMBean mbean = (BrokerViewMBean) MBeanServerInvocationHandler
				.newProxyInstance(conn, activeMQ,
						BrokerViewMBean.class, true);
        for (ObjectName name : mbean.getQueues()) {
            QueueViewMBean queueViewMBean = (QueueViewMBean) MBeanServerInvocationHandler
                    .newProxyInstance(conn, name, QueueViewMBean.class,
                            true);
            try {
                queueViewMBean.purge();
            } catch (Exception e) {
                e.printStackTrace();
                throw e;
            }
        }		
	}
	
}
