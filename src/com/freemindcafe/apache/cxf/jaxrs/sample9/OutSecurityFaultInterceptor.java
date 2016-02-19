package com.freemindcafe.apache.cxf.jaxrs.sample9;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.XMLMessage;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.log4j.Logger;

/***
 * 
 * @author KOMAN00S
 * 
 */
//Phase.PRE_PROTOCOL
public class OutSecurityFaultInterceptor extends AbstractPhaseInterceptor<XMLMessage>  {

	protected Logger logger = Logger.getLogger(OutSecurityFaultInterceptor.class);

	public OutSecurityFaultInterceptor(){
		super(Phase.PRE_PROTOCOL);
	}
	
	@Override
	public void handleMessage(XMLMessage arg0) throws Fault {
		System.out.println("OutSecurityFaultInterceptor-----------------");
		//SecurityContextHolder.getContext().setAuthentication(null);
		//SessionHolder.setSession(null);
	}
	
}
