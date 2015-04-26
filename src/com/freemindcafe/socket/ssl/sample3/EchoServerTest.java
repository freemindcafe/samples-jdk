package com.freemindcafe.socket.ssl.sample3;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class EchoServerTest {
	
	@Test
	public void ssl_server_that_demands_client_auth() {
		try {

			System.setProperty(
					"javax.net.ssl.trustStore","E:/personal/learnings/samples/samples-jdk/src/com/freemindcafe/socket/ssl/sample3/serverkeystore.jks");
			System.setProperty("javax.net.ssl.trustStorePassword", "password");
			System.setProperty(
					"javax.net.ssl.keyStore",
					"E:/personal/learnings/samples/samples-jdk/src/com/freemindcafe/socket/ssl/sample3/serverkeystore.jks");
			System.setProperty("javax.net.ssl.keyStorePassword", "password");
			System.setProperty("javax.net.debug", "ssl:handshake");
			
			SSLServerSocketFactory sslserversocketfactory = (SSLServerSocketFactory) SSLServerSocketFactory
					.getDefault();
			SSLServerSocket sslserversocket = (SSLServerSocket) sslserversocketfactory
					.createServerSocket(9999);
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
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
}
