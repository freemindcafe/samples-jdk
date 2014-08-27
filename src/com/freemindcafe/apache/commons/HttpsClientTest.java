package com.freemindcafe.apache.commons;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;


public class HttpsClientTest
{
 
  private static final Logger logger = Logger.getLogger (HttpsClientTest.class);

  @Test
  public void test()
  {
	  	BasicConfigurator.configure();
	  	System.setProperty("javax.net.ssl.trustStore","${EIP_HOME}/conf/systemProperties/localhostkeystore");
	  	final HttpClient client = new HttpClient();

		final PostMethod post = new PostMethod("https://nikhil-vm.emeter.com:9443/");
		String username = "abc";

		post.setRequestBody(new NameValuePair[] {
				new NameValuePair("username", username),
				new NameValuePair("password", "xyz") });
		
		try {
			logger.info("Attempt to get TGT for user [" + username + "]");
			
			//change the logging level for content of http message to INFO as in case of DEBUG mode  user name and password is getting printed
			Level oldLevel = Logger.getLogger("httpclient.wire.content").getLevel();
			Logger.getLogger("httpclient.wire.content").setLevel(Level.INFO);

			client.executeMethod(post);
			//switch back to original level.
			Logger.getLogger("httpclient.wire.content").setLevel(oldLevel);
			
			final String response = post.getResponseBodyAsString();
			
			switch (post.getStatusCode()) {
				case 201: {
					final Matcher matcher = Pattern.compile(".*action=\".*/(.*?)\".*").matcher(response);

					if (matcher.matches()) {
						logger.info("result - " + matcher.group(1));
					}

					logger.error("Successful ticket granting request, but no ticket found!");
					logger.error("Response (1k): "
						+ response.substring(0, Math.min(1024, response.length())));
					break;
				} default: {
					logger.error("Invalid response code (" + post.getStatusCode()
						+ ") from CAS server!");
					logger.error("Response (1k): "
					+ response.substring(0, Math.min(1024, response.length())));
					break;
				}
			}
		} catch (final IOException e) {
			logger.error("Exception while trying to get TGT from CAS server for USER - " + username, e);
		} finally {
			post.releaseConnection();
		}
		logger.info("result - " + "null");

  }
   

}