package com.freemindcafe.socket.ssl.sample5;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyStore;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManagerFactory;

import org.junit.Test;

public class EchoServerTest {
	
	@Test
	public void ssl_server_that_demands_client_auth_uses_custom_key_manager() throws Exception{
		
		System.setProperty("javax.net.debug", "ssl:handshake");
		
		KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
		KeyStore ts = KeyStore.getInstance("JKS");
		
		char[] passphrase = "password".toCharArray();
		
		ks.load(new FileInputStream("E:/personal/learnings/samples/samples-jdk/src/com/freemindcafe/socket/ssl/sample4/serverkeystore.jks"), passphrase);
		ts.load(new FileInputStream("E:/personal/learnings/samples/samples-jdk/src/com/freemindcafe/socket/ssl/sample4/serverkeystore.jks"), passphrase);
		
		KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
		kmf.init(ks, passphrase);
		
        TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
        tmf.init(ts);
        
//		final X509KeyManager origKm = (X509KeyManager)kmf.getKeyManagers()[0];
//		X509KeyManager km = new X509KeyManager() {
//
//			@Override
//			public String chooseClientAlias(String[] keyType,
//					Principal[] issuers, Socket socket) {
//				return origKm.chooseClientAlias(keyType, issuers, socket);
//			}
//
//			@Override
//			public String chooseServerAlias(String keyType,
//					Principal[] issuers, Socket socket) {
//		        //InetAddress remoteAddress = socket.getInetAddress();
//				return "serverkey";
//			}
//
//			@Override
//			public X509Certificate[] getCertificateChain(String alias) {
//				return origKm.getCertificateChain(alias);
//			}
//
//			@Override
//			public String[] getClientAliases(String keyType, Principal[] issuers) {
//				return origKm.getClientAliases(keyType, issuers);
//			}
//
//			@Override
//			public PrivateKey getPrivateKey(String alias) {
//				return origKm.getPrivateKey(alias);
//			}
//
//			@Override
//			public String[] getServerAliases(String keyType, Principal[] issuers) {
//				return origKm.getServerAliases(keyType, issuers);
//			}
//			
//		};
        
		SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
		sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
		//sslContext.init(new KeyManager[] { km }, null, null);
		
		SSLServerSocketFactory sslserversocketfactory = (SSLServerSocketFactory) SSLServerSocketFactory
				.getDefault();
		SSLServerSocket sslserversocket = (SSLServerSocket) sslserversocketfactory
				.createServerSocket(9999);
		sslserversocket.setEnabledCipherSuites(sslserversocket.getSupportedCipherSuites());
		//sslserversocket.setWantClientAuth(true);
		sslserversocket.setNeedClientAuth(true);
		SSLSocket sslsocket = (SSLSocket) sslserversocket.accept();

		InputStream inputstream = sslsocket.getInputStream();
		InputStreamReader inputstreamreader = new InputStreamReader(
				inputstream);
		BufferedReader bufferedreader = new BufferedReader(
				inputstreamreader);

		String string = null;
		while ((string = bufferedreader.readLine()) != null) {
			System.out.println("server printing ################");
			System.out.println(string);
			System.out.flush();
		}

		
	}

}
