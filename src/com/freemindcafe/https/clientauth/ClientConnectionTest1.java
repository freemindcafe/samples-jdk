package com.freemindcafe.https.clientauth;

import java.io.IOException;

import javax.net.ssl.SSLSocketFactory;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.methods.GetMethod;
 
 
public class ClientConnectionTest1
{
 static
 {
 System.setProperty("javax.net.ssl.trustStore", "E:/personal/learnings/samples/samples-jdk/src/com/freemindcafe/https/clientauth/clientkeystore1.jks");
 System.setProperty("javax.net.ssl.trustStorePassword", "password");
 System.setProperty("javax.net.ssl.keyStore", "E:/personal/learnings/samples/samples-jdk/src/com/freemindcafe/https/clientauth/clientkeystore1.jks");
 System.setProperty("javax.net.ssl.keyStorePassword", "password");
 System.setProperty("javax.net.debug", "ssl:handshake");
 }
 
   public static void main(String[] args) throws HttpException, IOException
   {
      HttpClient client = new HttpClient();
      GetMethod method = new GetMethod();
      //if you are not redirecting you can use port as localhost:8443
      method.setURI(new URI("https://localhost:443", false)); 
      //method.setURI(new URI("https://nikhil.emeter.com:443", false)); 
      client.executeMethod(method);
      System.out.println(method.getResponseBodyAsString());
   }
}
