package com.freemindcafe.apache.cxf.wsdl;
import org.apache.cxf.binding.soap.interceptor.SoapHeaderInterceptor;
import org.apache.cxf.configuration.security.AuthorizationPolicy;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.log4j.Logger;

//Phase.UNMARSHAL
public class BasicAuthInterceptor extends SoapHeaderInterceptor {

    protected static final Logger logger = Logger.getLogger(BasicAuthInterceptor.class);

    @Override public void handleMessage(Message message) throws Fault {
        // This is set by CXF
        AuthorizationPolicy policy = message.get(AuthorizationPolicy.class);
        if(policy != null){
        	System.out.println("BasicAuthInterceptor-----------------");
            System.out.println("usernname - " + policy.getUserName());
            System.out.println("password - " + policy.getPassword());
        }
    }
}
