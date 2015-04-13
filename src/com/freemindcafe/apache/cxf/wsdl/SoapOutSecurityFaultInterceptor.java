package com.freemindcafe.apache.cxf.wsdl;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.Phase;
import org.apache.log4j.Logger;

/***
 * 
 * @author KOMAN00S
 * 
 */
//Phase.PRE_PROTOCOL
public class SoapOutSecurityFaultInterceptor extends AbstractSoapInterceptor  {

	protected Logger logger = Logger.getLogger(SoapOutSecurityFaultInterceptor.class);

	public SoapOutSecurityFaultInterceptor(){
		super(Phase.PRE_PROTOCOL);
	}
	
	@Override
	public void handleMessage(SoapMessage arg0) throws Fault {
		System.out.println("SoapOutSecurityFaultInterceptor-----------------");
		//SecurityContextHolder.getContext().setAuthentication(null);
		//SessionHolder.setSession(null);
	}
	
}
