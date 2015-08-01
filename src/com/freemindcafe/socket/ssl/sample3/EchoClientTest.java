package com.freemindcafe.socket.ssl.sample3;

import static com.freemindcafe.utils.FileSystemUtils.currentDir;
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
	public void simple_client_connection_with_server_succeeds_as_client_and_server_keystore_has_each_others_key(){
		try {
			
			System.setProperty(
					"javax.net.ssl.trustStore",currentDir()+"/src/com/freemindcafe/socket/ssl/sample3/clientkeystore.jks");
			System.setProperty("javax.net.ssl.trustStorePassword", "password");
			System.setProperty(
					"javax.net.ssl.keyStore",
					currentDir()+"/src/com/freemindcafe/socket/ssl/sample3/clientkeystore.jks");
			System.setProperty("javax.net.ssl.keyStorePassword", "password");
			System.setProperty("javax.net.debug", "ssl:handshake");
			
			
			SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory
					.getDefault();
			SSLSocket sslsocket = (SSLSocket) sslsocketfactory.createSocket(
					"localhost", 9999);

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