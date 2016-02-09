package com.freemindcafe.apache.cxf.jaxrs.sample7;
import org.apache.cxf.configuration.security.AuthorizationPolicy;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.XMLMessage;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.log4j.Logger;

//Phase.UNMARSHAL
public class BasicAuthInterceptor extends AbstractPhaseInterceptor<XMLMessage> {
	
	public BasicAuthInterceptor(){
		super(Phase.UNMARSHAL);
	}

    protected static final Logger logger = Logger.getLogger(BasicAuthInterceptor.class);

    @Override public void handleMessage(XMLMessage message) throws Fault {
    	System.out.println("BasicAuthInterceptor-----------------");
        AuthorizationPolicy policy = message.get(AuthorizationPolicy.class);
        if(policy != null){
            System.out.println("usernname - " + policy.getUserName());
            System.out.println("password - " + policy.getPassword());
        }
        else{
        	throw new RuntimeException("This should be called with basic auth");
        }
    }
}
