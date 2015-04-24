package com.freemindcafe.apache.cxf.jaxrs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

import org.apache.cxf.configuration.jsse.TLSServerParameters;
import org.apache.cxf.configuration.security.ClientAuthentication;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import org.apache.cxf.transport.http_jetty.JettyHTTPServerEngineFactory;
import org.junit.Test;

import com.freemindcafe.apache.cxf.wsdl.SSLInterceptor;

public class Server {
	
	@Test
	public void start_server_without_ssl() throws Exception{		
		OrderInfoImpl implementor = new OrderInfoImpl();
		JAXRSServerFactoryBean svrFactory = new JAXRSServerFactoryBean();
		svrFactory.setServiceClass(OrderInfoImpl.class);
		svrFactory.setAddress("http://localhost:9001/bizsvc");
		svrFactory.setServiceBean(implementor);
		//in interceptors
		svrFactory.getInInterceptors().add(new LoggingInInterceptor());
		svrFactory.getInInterceptors().add(new BasicAuthInterceptor());
		// out normal response interceptor
		svrFactory.getOutInterceptors().add(new LoggingOutInterceptor());
		svrFactory.getOutInterceptors().add(new OutSecurityInterceptor());		
		//out fault interceptor
		svrFactory.getOutFaultInterceptors().add(new OutSecurityFaultInterceptor());

		org.apache.cxf.endpoint.Server server = svrFactory.create();		
		String endpoint = server.getEndpoint().getEndpointInfo().getAddress();        
        System.out.println("Server started at " + endpoint);
        synchronized(server){
        	server.wait();	
        }
	}
	
	@Test
	public void start_server_with_2_way_ssl() throws Exception{		
		OrderInfoImpl implementor = new OrderInfoImpl();
		JAXRSServerFactoryBean svrFactory = new JAXRSServerFactoryBean();
		svrFactory.setServiceClass(OrderInfoImpl.class);
		svrFactory.setAddress("https://localhost:9001/bizsvc");
		svrFactory.setServiceBean(implementor);
		//in interceptors
		svrFactory.getInInterceptors().add(new LoggingInInterceptor());
		svrFactory.getInInterceptors().add(new BasicAuthInterceptor());
		svrFactory.getInInterceptors().add(new SSLInterceptor());
		// out normal response interceptor
		svrFactory.getOutInterceptors().add(new LoggingOutInterceptor());
		svrFactory.getOutInterceptors().add(new OutSecurityInterceptor());		
		//out fault interceptor
		svrFactory.getOutFaultInterceptors().add(new OutSecurityFaultInterceptor());

		svrFactory = configureSSLOnTheServer(svrFactory, 9001);	
		org.apache.cxf.endpoint.Server server = svrFactory.create();		
		String endpoint = server.getEndpoint().getEndpointInfo().getAddress();        
        System.out.println("Server started at " + endpoint);
        synchronized(server){
        	server.wait();	
        }
	}

    private JAXRSServerFactoryBean configureSSLOnTheServer(JAXRSServerFactoryBean sf, int port) {
        try {
        	System.setProperty("javax.net.debug", "ssl:handshake");
        	TLSServerParameters tlsParams = new TLSServerParameters();
            KeyStore keyStore = KeyStore.getInstance("JKS");
            String password = "password";
            File keystoreFile = new File("src\\com\\freemindcafe\\apache\\cxf\\jaxrs\\serverkeystore.jks");
            keyStore.load(new FileInputStream(keystoreFile), password.toCharArray());
            KeyManagerFactory keyFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            keyFactory.init(keyStore, password.toCharArray());
            KeyManager[] km = keyFactory.getKeyManagers();
            tlsParams.setKeyManagers(km);
 
            File truststoreFile = new File("src\\com\\freemindcafe\\apache\\cxf\\jaxrs\\serverkeystore.jks");
            keyStore.load(new FileInputStream(truststoreFile), password.toCharArray());
            TrustManagerFactory trustFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustFactory.init(keyStore);
            TrustManager[] tm = trustFactory.getTrustManagers();
            tlsParams.setTrustManagers(tm);
//            FiltersType filter = new FiltersType();
//            filter.getInclude().add(".*_EXPORT_.*");
//            filter.getInclude().add(".*_EXPORT1024_.*");
//            filter.getInclude().add(".*_WITH_DES_.*");
//            filter.getInclude().add(".*_WITH_NULL_.*");
//            filter.getExclude().add(".*_DH_anon_.*");
//            tlsParams.setCipherSuitesFilter(filter);
            ClientAuthentication ca = new ClientAuthentication();
            ca.setRequired(true);
            ca.setWant(true);
            tlsParams.setClientAuthentication(ca);
            JettyHTTPServerEngineFactory factory = new JettyHTTPServerEngineFactory();
            factory.setTLSServerParametersForPort(port, tlsParams);
        } catch (KeyStoreException kse) {
            System.out.println("Security configuration failed with the following: " + kse.getCause());
        } catch (NoSuchAlgorithmException nsa) {
            System.out.println("Security configuration failed with the following: " + nsa.getCause());
        } catch (FileNotFoundException fnfe) {
            System.out.println("Security configuration failed with the following: " + fnfe.getCause());
        } catch (UnrecoverableKeyException uke) {
            System.out.println("Security configuration failed with the following: " + uke.getCause());
        } catch (CertificateException ce) {
            System.out.println("Security configuration failed with the following: " + ce.getCause());
        } catch (GeneralSecurityException gse) {
            System.out.println("Security configuration failed with the following: " + gse.getCause());
        } catch (IOException ioe) {
            System.out.println("Security configuration failed with the following: " + ioe.getCause());
        }
 
        return sf;
    }	

}

