package com.freemindcafe.apache.cxf.wsdl.sample1;
import org.apache.cxf.binding.soap.interceptor.SoapHeaderInterceptor;
import org.apache.cxf.configuration.security.AuthorizationPolicy;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.log4j.Logger;

//\cond HIDDEN_SYMBOLS
//Phase.UNMARSHAL
public class BasicAuthInterceptor extends SoapHeaderInterceptor {

    protected static final Logger logger = Logger.getLogger(BasicAuthInterceptor.class);

    @Override public void handleMessage(Message message) throws Fault {
    	System.out.println("BasicAuthInterceptor-----------------");
        // This is set by CXF
        AuthorizationPolicy policy = message.get(AuthorizationPolicy.class);
        if(policy != null){
            System.out.println("usernname - " + policy.getUserName());
            System.out.println("password - " + policy.getPassword());
        }
    }
}
//\endcond
