package com.freemindcafe.https.clientauth;

import java.io.IOException;
import java.security.cert.X509Certificate;

import javax.naming.InvalidNameException;
import javax.naming.ldap.LdapName;
import javax.naming.ldap.Rdn;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import org.apache.catalina.valves.ValveBase;

public class CustomValve extends ValveBase{

	@Override
	public void invoke(Request arg0, Response arg1) throws IOException,
			ServletException {
		HttpServletRequest req = arg0;
		X509Certificate certs[] = 
			    (X509Certificate[])req.getAttribute("javax.servlet.request.X509Certificate");
		String dn = certs[0].getSubjectX500Principal().getName();
		LdapName ldapDN;
		try {
			ldapDN = new LdapName(dn);
			for(Rdn rdn: ldapDN.getRdns()) {
				System.err.println(rdn.getType() + " -> " + rdn.getValue());
			}
		} catch (InvalidNameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
