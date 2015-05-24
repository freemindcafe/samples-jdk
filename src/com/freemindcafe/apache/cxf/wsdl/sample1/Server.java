package com.freemindcafe.apache.cxf.wsdl.sample1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.xml.namespace.QName;

import org.apache.cxf.Bus;
import org.apache.cxf.BusFactory;
import org.apache.cxf.configuration.jsse.TLSServerParameters;
import org.apache.cxf.configuration.security.ClientAuthentication;
import org.apache.cxf.configuration.security.FiltersType;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import org.apache.cxf.transport.http_jetty.JettyHTTPServerEngineFactory;
import org.apache.hello_world_soap_http.Greeter;
import org.apache.hello_world_soap_http.GreeterImpl;
import org.apache.hello_world_soap_http.PingMeFault;
import org.apache.hello_world_soap_http.SOAPService;
import org.junit.Test;

public class Server {
	
	/**
	 * It has basic auth, wsse security token as in interceptor.
	 * It also has out and fault interceptors.
	 * Interceptors prints messages on server console.
	 */
	@Test
	public void start_server_without_ssl() throws Exception{
		
		GreeterImpl implementor = new GreeterImpl();
		JaxWsServerFactoryBean svrFactory = new JaxWsServerFactoryBean();
		svrFactory.setServiceClass(Greeter.class);
		svrFactory.setAddress("http://localhost:9001/SoapContext/SoapPort");
		svrFactory.setServiceBean(implementor);
		//in interceptors
		svrFactory.getInInterceptors().add(new LoggingInInterceptor());
		svrFactory.getInInterceptors().add(new BasicAuthInterceptor());
		Map<String, Object> properties = new HashMap<>();
		properties.put("action", "UsernameToken");
		properties.put("passwordType", "PasswordText");
		properties.put("passwordCallbackRef", new SecurityContextCallback());
		svrFactory.getInInterceptors().add(new WSSecurityInterceptor(properties));		
		// out normal response interceptor
		svrFactory.getOutInterceptors().add(new LoggingOutInterceptor());
		svrFactory.getOutInterceptors().add(new SoapOutSecurityInterceptor());		
		//out fault interceptor
		svrFactory.getOutFaultInterceptors().add(new SoapOutSecurityFaultInterceptor());
		
		org.apache.cxf.endpoint.Server server = svrFactory.create();
		String endpoint = server.getEndpoint().getEndpointInfo().getAddress();        
        System.out.println("Server started at " + endpoint);
 
        synchronized(server){
        	server.wait();	
        }	
	}
	
	/**
	 * @throws Exception
	 */
	@Test
	public void start_server_without_ssl_and_use_client_in_same_jvm_with_interceptors_on_endpoint() throws Exception{

		GreeterImpl implementor = new GreeterImpl();
		JaxWsServerFactoryBean svrFactory = new JaxWsServerFactoryBean();
		svrFactory.setServiceClass(Greeter.class);
		svrFactory.setAddress("http://localhost:9001/SoapContext/SoapPort");
		svrFactory.setServiceBean(implementor);
		//in interceptors
		svrFactory.getInInterceptors().add(new LoggingInInterceptor());
		svrFactory.getInInterceptors().add(new BasicAuthInterceptor());
		Map<String, Object> properties = new HashMap<>();
		properties.put("action", "UsernameToken");
		properties.put("passwordType", "PasswordText");
		properties.put("passwordCallbackRef", new SecurityContextCallback());
		svrFactory.getInInterceptors().add(new WSSecurityInterceptor(properties));		
		// out normal response interceptor
		svrFactory.getOutInterceptors().add(new LoggingOutInterceptor());
		svrFactory.getOutInterceptors().add(new SoapOutSecurityInterceptor());		
		//out fault interceptor
		svrFactory.getOutFaultInterceptors().add(new SoapOutSecurityFaultInterceptor());
		
		org.apache.cxf.endpoint.Server server = svrFactory.create();		
		String endpoint = server.getEndpoint().getEndpointInfo().getAddress();        
        System.out.println("Server started at " + endpoint);
        
        //client
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.getInInterceptors().add(new LoggingInInterceptor());
		factory.getOutInterceptors().add(new LoggingOutInterceptor());
		factory.setServiceClass(Greeter.class);
		factory.setAddress("http://localhost:9000/SoapContext/SoapPort");
		Greeter client = (Greeter) factory.create();

		client.greetMeOneWay("abc");
	    
        synchronized(server){
        	server.wait();	
        }
        
	}
	
	/**
	 * @throws Exception
	 */
	@Test
	public void start_server_without_ssl_and_use_client_in_same_jvm_with_interceptors_on_bus() throws Exception{

		Bus bus =  BusFactory.getDefaultBus();	
		
		GreeterImpl implementor = new GreeterImpl();
		JaxWsServerFactoryBean svrFactory = new JaxWsServerFactoryBean();
		svrFactory.setServiceClass(Greeter.class);
		svrFactory.setAddress("http://localhost:9001/SoapContext/SoapPort");
		svrFactory.setServiceBean(implementor);
		//in interceptors
		bus.getInInterceptors().add(new LoggingInInterceptor());
		bus.getInInterceptors().add(new BasicAuthInterceptor());
		Map<String, Object> properties = new HashMap<>();
		properties.put("action", "UsernameToken");
		properties.put("passwordType", "PasswordText");
		properties.put("passwordCallbackRef", new SecurityContextCallback());
		bus.getInInterceptors().add(new WSSecurityInterceptor(properties));		
		// out normal response interceptor
		bus.getOutInterceptors().add(new LoggingOutInterceptor());
		bus.getOutInterceptors().add(new SoapOutSecurityInterceptor());		
		//out fault interceptor
		bus.getOutFaultInterceptors().add(new SoapOutSecurityFaultInterceptor());
		
		org.apache.cxf.endpoint.Server server = svrFactory.create();		
		String endpoint = server.getEndpoint().getEndpointInfo().getAddress();        
        System.out.println("Server started at " + endpoint);
        
        //client
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.getInInterceptors().add(new LoggingInInterceptor());
		factory.getOutInterceptors().add(new LoggingOutInterceptor());
		factory.setServiceClass(Greeter.class);
		factory.setAddress("http://localhost:9000/SoapContext/SoapPort");
		Greeter client = (Greeter) factory.create();

		client.greetMeOneWay("abc");
	    
        synchronized(server){
        	server.wait();	
        }
        
	}	
	
	/**
	 * @throws Exception
	 */
	@Test
	public void start_server_with_2_way_ssl() throws Exception{
		GreeterImpl implementor = new GreeterImpl();
		JaxWsServerFactoryBean svrFactory = new JaxWsServerFactoryBean();
		svrFactory.setServiceClass(Greeter.class);
		svrFactory.setAddress("https://localhost:9001/SoapContext/SoapPort");
		svrFactory.setServiceBean(implementor);	
		
		//in interceptors 
		//Order of interceptors is decided by phases
		//RECEIVE -> (PRE/USER/POST)_PROTOCOL (This could have muliple interceptors) -> UNMARSHAL
		svrFactory.getInInterceptors().add(new LoggingInInterceptor());
		svrFactory.getInInterceptors().add(new BasicAuthInterceptor());
		Map<String, Object> properties = new HashMap<>();
		properties.put("action", "UsernameToken");
		properties.put("passwordType", "PasswordText");
		properties.put("passwordCallbackRef", new SecurityContextCallback());
		svrFactory.getInInterceptors().add(new WSSecurityInterceptor(properties));
		svrFactory.getInInterceptors().add(new SSLInterceptor());		
		// out normal response interceptor
		svrFactory.getOutInterceptors().add(new LoggingOutInterceptor());
		svrFactory.getOutInterceptors().add(new SoapOutSecurityInterceptor());		
		//out fault interceptor
		svrFactory.getOutFaultInterceptors().add(new SoapOutSecurityFaultInterceptor());
		
		svrFactory = configureSSLOnTheServer(svrFactory, 9001);		
		org.apache.cxf.endpoint.Server server = svrFactory.create();		
		String endpoint = server.getEndpoint().getEndpointInfo().getAddress();        
        System.out.println("Server started at " + endpoint);
        synchronized(server){
        	server.wait();	
        }
	}
	
	 
    private JaxWsServerFactoryBean configureSSLOnTheServer(JaxWsServerFactoryBean sf, int port) {
        try {
        	System.setProperty("javax.net.debug", "ssl:handshake");
        	TLSServerParameters tlsParams = new TLSServerParameters();
            KeyStore keyStore = KeyStore.getInstance("JKS");
            String password = "password";
            File keystoreFile = new File("src\\com\\freemindcafe\\apache\\cxf\\wsdl\\serverkeystore.jks");
            keyStore.load(new FileInputStream(keystoreFile), password.toCharArray());
            KeyManagerFactory keyFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            keyFactory.init(keyStore, password.toCharArray());
            KeyManager[] km = keyFactory.getKeyManagers();
            tlsParams.setKeyManagers(km);
 
            File truststoreFile = new File("src\\com\\freemindcafe\\apache\\cxf\\wsdl\\serverkeystore.jks");
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
