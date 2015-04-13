package com.freemindcafe.apache.cxf.wsdl;

import java.io.File;
import java.net.URL;

import javax.xml.namespace.QName;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.hello_world_soap_http.Greeter;
import org.apache.hello_world_soap_http.PingMeFault;
import org.apache.hello_world_soap_http.SOAPService;
import org.junit.Test;

public final class Client {
	
	@Test
	public void soap_service_based_client() throws Exception{

		QName SERVICE_NAME = new QName("http://apache.org/hello_world_soap_http","SOAPService");
	    
		URL wsdlURL;
	    File wsdlFile = new File("src\\com\\freemindcafe\\apache\\cxf\\wsdl\\helloworld.wsdl");
	    wsdlURL = wsdlFile.toURL();
	 
	    System.out.println(wsdlURL);
	    SOAPService ss = new SOAPService(wsdlURL, SERVICE_NAME);
	    Greeter port = ss.getSoapPort();
	    String resp;
	 
	    System.out.println("Invoking sayHi...");
	    resp = port.sayHi();
	    System.out.println("Server responded with: " + resp);
	    System.out.println();
	 
	    System.out.println("Invoking greetMe...");
	    resp = port.greetMe(System.getProperty("user.name"));
	    System.out.println("Server responded with: " + resp);
	    System.out.println();
	 
	    System.out.println("Invoking greetMeOneWay...");
	    port.greetMeOneWay(System.getProperty("user.name"));
	    System.out.println("No response from server as method is OneWay");
	    System.out.println();
	 
	    try {
	      System.out.println("Invoking pingMe, expecting exception...");
	      port.pingMe();
	    } catch (PingMeFault ex) {
	      System.out.println("Expected exception: PingMeFault has occurred.");
	      System.out.println(ex.toString());
	    }
	    System.exit(0);
		
	}
	
	@Test
	public void JaxWsProxyFactoryBean_based_client(){
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.getInInterceptors().add(new LoggingInInterceptor());
		factory.getOutInterceptors().add(new LoggingOutInterceptor());
		factory.setServiceClass(Greeter.class);
		factory.setAddress("http://localhost:9001/SoapContext/SoapPort");
		Greeter client = (Greeter) factory.create();

		String reply = client.greetMe("nik");
		System.out.println("Server said: " + reply);
		System.exit(0);
	}
	 
}
