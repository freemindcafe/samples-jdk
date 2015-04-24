package com.freemindcafe.apache.cxf.jaxrs;

import java.security.cert.X509Certificate;

import javax.naming.InvalidNameException;
import javax.naming.ldap.LdapName;
import javax.naming.ldap.Rdn;
import javax.servlet.http.HttpServletRequest;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.XMLMessage;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.transport.http.AbstractHTTPDestination;

//Phase.PRE_PROTOCOL
public class SSLInterceptor extends AbstractPhaseInterceptor<XMLMessage>{
	
	public SSLInterceptor(){
		super(Phase.PRE_PROTOCOL);
	}
	
	public void handleMessage(XMLMessage message) throws Fault {
		System.out.println("SSLInterceptor-----------------");
		//if you want to read more http header messages, just use get method to obtain from  HttpServletRequest.
		HttpServletRequest request = (HttpServletRequest) message.get(AbstractHTTPDestination.HTTP_REQUEST);
		X509Certificate certs[] = 
				(X509Certificate[])request.getAttribute("javax.servlet.request.X509Certificate");
		String dn = certs[0].getSubjectX500Principal().getName();
		LdapName ldapDN;
		try {
			ldapDN = new LdapName(dn);
			for(Rdn rdn: ldapDN.getRdns()) {
				System.out.println(rdn.getType() + " -> " + rdn.getValue());
			}
		} catch (InvalidNameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      
	}


}
