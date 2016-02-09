package com.freemindcafe.apache.cxf.jaxrs.sample7;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.junit.Test;

public class Server {
	
	@Test
	public void test() throws Exception{
		JAXRSServerFactoryBean sf = new JAXRSServerFactoryBean();
		sf.setResourceClasses(TicketsResource.class);
		sf.setAddress("http://localhost:9000/tickets");
		
		//in interceptors
		sf.getInInterceptors().add(new LoggingInInterceptor());
		sf.getInInterceptors().add(new BasicAuthInterceptor());
		// out normal response interceptor
		sf.getOutInterceptors().add(new LoggingOutInterceptor());
		sf.getOutInterceptors().add(new OutSecurityInterceptor());		
		//out fault interceptor
		sf.getOutFaultInterceptors().add(new OutSecurityFaultInterceptor());
        
		sf.create();
		synchronized (sf) {
			sf.wait();
		}
	}

}
