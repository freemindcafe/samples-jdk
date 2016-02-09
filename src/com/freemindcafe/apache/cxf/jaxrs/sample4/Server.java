package com.freemindcafe.apache.cxf.jaxrs.sample4;

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
	public void send_list_in_the_response() throws Exception{
		JAXRSServerFactoryBean sf = new JAXRSServerFactoryBean();
		sf.setResourceClasses(TicketsResource.class);
		sf.setAddress("http://localhost:9000/");
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
