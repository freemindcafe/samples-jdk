/**
 * \brief This sample uses JMXConnectorServer and client uses JMXConnector to access MBean.
 * 
 * <p>
 * This sample uses JMXConnectorServer and client uses JMXConnector to access MBean.
 * </p>
 * <h1>Server</h1>
 * <ul>
 * <li>IHelloService is service interface.</li>
 * <li>HelloServiceMBean is an managed bean interface which extends from IHelloService.</li>
 * <li>HelloService implements HelloServiceMBean interface.</li>
 * <li>Server registers MBean with MBeanServer.</li>
 * <li>Server also need to start the registry service at some port.</li>
 * </ul>
 * <h1>Client</h1>
 * <ul>
 * <li>HelloServiceImpl uses hostname, port and object name to connect to JMXConnectorServer.</li>
 * <li>HelloServiceImpl implements IHelloService service interface.</li>
 * <li>HelloServiceImpl uses JMXConnector to get the MBean which can be type cast to IHelloService.</li>
 * </ul>
 */
package com.freemindcafe.jmx.sample2;