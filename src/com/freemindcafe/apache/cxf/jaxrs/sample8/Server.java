package com.freemindcafe.apache.cxf.jaxrs.sample8;

import java.util.ArrayList;
import java.util.List;

import org.apache.cxf.interceptor.Interceptor;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.message.Message;
import org.junit.Test;

public class Server {
	
	@Test
	public void test() throws Exception{
		JAXRSServerFactoryBean sf = new JAXRSServerFactoryBean();
		TicketsResource ticketsResource = new TicketsResource();
		sf.setServiceBean(ticketsResource);
		sf.setAddress("http://localhost:9000/tickets");
       final List<Interceptor<? extends Message>> list = new ArrayList<Interceptor<? extends Message>>();
        list.add(new LoggingInInterceptor());
        list.add(new LoggingOutInterceptor());
        sf.setInInterceptors(list);        
		sf.create();
		synchronized (sf) {
			sf.wait();
		}
	}

}
