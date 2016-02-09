package com.freemindcafe.apache.cxf.jaxrs.sample7;

import javax.servlet.http.HttpServletRequest;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
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
public class OutSecurityInterceptor extends AbstractPhaseInterceptor<XMLMessage>  {

	protected Logger logger = Logger.getLogger(OutSecurityInterceptor.class);

	public OutSecurityInterceptor(){
		super(Phase.PRE_PROTOCOL);
	}
	
	@Override
	public void handleMessage(XMLMessage arg0) throws Fault {
		System.out.println("OutSecurityInterceptor-----------------");
		//EIP-19204 starts
		Message inMessage = arg0.getExchange().getInMessage();
		
		//IN message can be null in case of async response
		if(inMessage != null) {
			HttpServletRequest req = (HttpServletRequest) inMessage.get("HTTP.REQUEST");
			
			if(req.getSession(false) != null) {
				logger.debug("invalidating the http session");
				req.getSession().invalidate();
			} else {
				logger.warn(" No session found Not able to invalidate http session");
			}

			//This should be done only when we are processing a request and sending a response back
			//As this intercepter will also be called in case we are sending a web service request to third party web server and
			//getting the response back. In this case, we don't want to clear the authentication.
			logger.debug("Exiting current user");
			//SecurityContextHolder.getContext().setAuthentication(null);
			//SessionHolder.setSession(null);
		} else {
			logger.warn("http request is null Not able to invalidate http session");
		}
		//EIP-19204 ends
	}

}
