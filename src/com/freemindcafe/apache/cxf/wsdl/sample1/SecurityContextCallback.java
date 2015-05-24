package com.freemindcafe.apache.cxf.wsdl.sample1;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.log4j.Logger;
import org.apache.ws.security.WSPasswordCallback;

//\cond HIDDEN_SYMBOLS
/**
 * CXF Password Callback used to retrieve username/password from web service
 * user request
 * 
 * @author li00000y
 * 
 */
public class SecurityContextCallback
		implements
			CallbackHandler {

	private static Logger logger = Logger
			.getLogger(SecurityContextCallback.class);


	@Override
	public void handle(Callback[] callbacks) throws IOException,
			UnsupportedCallbackException {

		for (int i = 0; i < callbacks.length; i++) {

			WSPasswordCallback pc = (WSPasswordCallback) callbacks[i];

			if (pc.getUsage() == WSPasswordCallback.USERNAME_TOKEN
					|| pc.getUsage() == WSPasswordCallback.USERNAME_TOKEN_UNKNOWN) {

				int usage = pc.getUsage();
				System.out.println("SecurityContextCallback-----------------");
	            System.out.println("usernname - " + pc.getIdentifier());
	            System.out.println("password - " + pc.getPassword());

			}
		}

		logger.error("No Username/Password found");

	}


}
//\endcond
