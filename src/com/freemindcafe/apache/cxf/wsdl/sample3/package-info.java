/**
 * \brief \htmlonly <font size="3" color="red">Incomplete ??</font> \endhtmlonly
 * \brief CXF 3.0.3
 * 		  Generates java code from xsd and wsdl. Verifies basic, wsse token and 2 way auth interceptors. 
 * 		  Uses soap out and fault interceptors.
 * 		  Uses SOAP UI as a client.
 * 
 * <h1>Setup</h1>
 * <ul>
 * <li>helloworld.wsdl file had xsd types definition and wsdl service definition</li>
 * <li>build.xml has apache-cxf-wsdl-cxfWSDLToJava target, this will generated the source code under build/generated-src using xmlbeans binding.</li>
 * <li>include build/generated-src in build path as source folder.</li>
 * </ul>
 * <h2>Generate the Client and Server Keystores</h2>
 * <ul>
 * <li>keytool -genkeypair -alias serverkey -keyalg RSA -dname "CN=localhost,OU=Organization Unit,O=Organization,L=City,S=State,C=IN" -keypass password -keystore serverkeystore.jks -storepass password</li>
 * <li>keytool -genkeypair -alias clientkey -keyalg RSA -dname "CN=localhost,OU=Organization Unit,O=Organization,L=City,S=State,C=IN" -keypass password -storepass password -keystore clientkeystore.jks</li>
 * </ul>
 * <h2>Install the client&rsquo;s public certificate in to the server&rsquo;s keystore</h2>
 * <ul>
 * <li>keytool -exportcert -alias clientkey -file client-public.cer -keystore clientkeystore.jks -storepass password</li>
 * <li>keytool -importcert -keystore serverkeystore.jks -alias clientcert -file client-public.cer -storepass password -noprompt</li>
 * </ul>
 * 
 * <h2>Export the Server&rsquo;s Public Certificate and Import it in to the client&rsquo;s Keystore</h2>
 * <ul>
 * <li>keytool -exportcert -alias serverkey -file server-public.cer -keystore serverkeystore.jks -storepass password</li>
 * <li>keytool -importcert -keystore clientkeystore.jks -alias servercert -file server-public.cer -storepass password -noprompt</li>
 * </ul>
 * 
 * <h1>SOAP UI Project</h1>
 * 	<pre>
 * 	Also has client and is best to use these clients as basic auth and wsse security headers can be easily specified.
 * 	Mock services are of limited use as tricky to specify basic auth and wsse security setup. Moreover that wouldn't be of cxf formats. Hence can not be used in real projects.
 * </pre>
 * 
 * 
 */
package com.freemindcafe.apache.cxf.wsdl.sample3;