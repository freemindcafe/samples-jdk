package com.freemindcafe.socket.ssl.sample4;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

import org.junit.Test;

import java.io.*;

/**
 * @author IC033920
 *
 */
public class EchoClientTest {

	@Test
	public void simple_client_connectes_with_server_fails_as_server_presents_serverkey1_and_client_has_the_public_cert_of_serverkey(){
		try {
			
			System.setProperty(
					"javax.net.ssl.trustStore","E:/personal/learnings/samples/samples-jdk/src/com/freemindcafe/socket/ssl/sample4/clientkeystore.jks");
			System.setProperty("javax.net.ssl.trustStorePassword", "password");
			System.setProperty(
					"javax.net.ssl.keyStore",
					"E:/personal/learnings/samples/samples-jdk/src/com/freemindcafe/socket/ssl/sample4/clientkeystore.jks");
			System.setProperty("javax.net.ssl.keyStorePassword", "password");
			System.setProperty("javax.net.debug", "ssl:handshake");
			
			
			SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory
					.getDefault();
			SSLSocket sslsocket = (SSLSocket) sslsocketfactory.createSocket(
					"localhost", 9999);
			sslsocket.setEnabledCipherSuites(sslsocket.getSupportedCipherSuites());

			InputStream inputstream = System.in;
			InputStreamReader inputstreamreader = new InputStreamReader(
					inputstream);
			BufferedReader bufferedreader = new BufferedReader(
					inputstreamreader);

			OutputStream outputstream = sslsocket.getOutputStream();
			OutputStreamWriter outputstreamwriter = new OutputStreamWriter(
					outputstream);
			BufferedWriter bufferedwriter = new BufferedWriter(
					outputstreamwriter);

			String string = null;
			while ((string = bufferedreader.readLine()) != null) {
				bufferedwriter.write(string + '\n');
				bufferedwriter.flush();
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
}