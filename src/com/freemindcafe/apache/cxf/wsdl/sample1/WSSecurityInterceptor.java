package com.freemindcafe.apache.cxf.wsdl.sample1;

import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.headers.Header;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.ws.security.wss4j.WSS4JInInterceptor;
import org.apache.log4j.Logger;

//\cond HIDDEN_SYMBOLS
/**
 * 
 * @author li00000y
 * 
 */
//Phase.PRE_PROTOCOL
public class WSSecurityInterceptor extends WSS4JInInterceptor{

	protected Logger log = Logger.getLogger(WSSecurityInterceptor.class);

	public WSSecurityInterceptor() {
		super();
	}

	public WSSecurityInterceptor(Map<String, Object> properties) {
		super(properties);
    	//setBefore(MustUnderstandInterceptor.class.getName());
		
	}

	public void handleMessage(SoapMessage message) throws Fault {
		try { 
			System.out.println("WSSecurityInterceptor-----------------");
   		    boolean securityHeaderExists = false;
    		List<Header> headers = message.getHeaders();
    		for(Header head : headers){
    			if( head.getName().getLocalPart().equals("Security")){
    				securityHeaderExists = true;
    				break;
    			}

    		}
    		    
    		if( !securityHeaderExists){
    			return;
    		}

		    super.handleMessage(message);
			
		} catch (Fault f) {
		    f.setMessage("Authentication failed");
			f.setFaultCode(new QName("Client"));
			
			throw f;
		} catch (Exception ex) {
		    Fault fault = new Fault(new Exception("Authentication failed"));
	        fault.setFaultCode(new QName("Client"));
	        throw fault;
		}
	}

	@Override
	public void handleFault(SoapMessage message) {
    	//In case of error in intercepter chain, this will clear the authentication for this thread
    	//SecurityContextHolder.getContext().setAuthentication(null);		
    	//.setSession(null);
		super.handleFault(message);
	}
	
}
//\endcond
