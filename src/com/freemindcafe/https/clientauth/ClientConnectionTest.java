package com.freemindcafe.https.clientauth;

import java.io.IOException;

import javax.net.ssl.SSLSocketFactory;
import static com.freemindcafe.utils.FileSystemUtils.currentDir;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.methods.GetMethod;
 
 
public class ClientConnectionTest
{
 static
 {
 System.setProperty("javax.net.ssl.trustStore", currentDir()+"/src/com/freemindcafe/https/clientauth/clientkeystore.jks");
 System.setProperty("javax.net.ssl.trustStorePassword", "password");
 System.setProperty("javax.net.ssl.keyStore", currentDir()+"/src/com/freemindcafe/https/clientauth/clientkeystore.jks");
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
