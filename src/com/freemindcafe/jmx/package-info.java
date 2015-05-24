/**
 * <h1>Server</h1>
 * <ul>
 * <li>The MBean server is a registry for MBeans.</li>
 * <li>There could be multiple MBean servers.</li>
 * <li>
 * A connector server can be attached to an MBean server in one of two ways. Either the MBean server to which it is attached is specified when the 
 * connector server is constructed, or the connector server is registered as an MBean in the MBean server to which it is attached.
 * </li>
 * <li>JMXConnectorServer is the interface.</li>
 * <li>RMIConnectorServer is used normally.</li>
 * 
 * <li>JMXConnectorServer's address is specified by JMXServiceURL [service:jmx:rmi:///jndi/rmi://localhost:1999/EIPRMIConnector]</li>
 * </ul>
 * <h1>client</h1>
 * <ul>
 * <li>Uses JMXServiceURL [service:jmx:rmi:///jndi/rmi://localhost:1999/EIPRMIConnector] and gets JMXConnector object.</li>
 * <li>Using JMXConnector, MBeanServerConnection object is fetched.</li>
 * </ul>
 * 
 * 
 */
package com.freemindcafe.jmx;