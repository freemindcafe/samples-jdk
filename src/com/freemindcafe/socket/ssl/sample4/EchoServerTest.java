package com.freemindcafe.socket.ssl.sample4;

import static com.freemindcafe.utils.FileSystemUtils.currentDir;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509KeyManager;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.security.KeyStore;
import java.security.Principal;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;

public class EchoServerTest {
	
	@Test
	public void ssl_server_that_demands_client_auth() {
		try {

			System.setProperty(
					"javax.net.ssl.trustStore",currentDir()+"/src/com/freemindcafe/socket/ssl/sample4/serverkeystore.jks");
			System.setProperty("javax.net.ssl.trustStorePassword", "password");
			System.setProperty(
					"javax.net.ssl.keyStore",
					currentDir()+"/src/com/freemindcafe/socket/ssl/sample4/serverkeystore.jks");
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
