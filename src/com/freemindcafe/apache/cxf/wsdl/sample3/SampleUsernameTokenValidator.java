package com.freemindcafe.apache.cxf.wsdl.sample3;

import org.apache.wss4j.common.ext.WSSecurityException;
import org.apache.wss4j.dom.handler.RequestData;
import org.apache.wss4j.dom.message.token.UsernameToken;
import org.apache.wss4j.dom.validate.Credential;
import org.apache.wss4j.dom.validate.UsernameTokenValidator;

public class SampleUsernameTokenValidator extends UsernameTokenValidator  {

	 public Credential validate( Credential credential, RequestData request ) throws WSSecurityException {
	        UsernameToken userToken = credential.getUsernametoken();
	        final String userId = userToken.getName();
	        final String password = userToken.getPassword();    
			System.out.println("EmeterUsernameTokenValidator-----------------");
            System.out.println("usernname - " + userId);
            System.out.println("password - " + password);   
			return credential;
	 }
}
