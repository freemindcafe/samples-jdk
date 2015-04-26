/**
 * Server has clients public certificate but it also has additional serverkey1. This keys public certificate is not imported in clients store.
 * Hence client auth fails as server sends the certficate for serverkey1.
 * 
 * <h1>Setup</h1>
 * <h2>Generate the Client and Server Keystores</h2>
 * <ul>
 * <li>keytool -genkeypair -alias serverkey -keyalg RSA -dname "CN=localhost,OU=Organization Unit,O=Organization,L=City,S=State,C=IN" -keypass password -keystore serverkeystore.jks -storepass password</li>
 * <li>keytool -genkeypair -alias clientkey -keyalg RSA -dname "CN=localhost,OU=Organization Unit,O=Organization,L=City,S=State,C=IN" -keypass password -storepass password -keystore clientkeystore.jks</li>
 * </ul>
 * <h2>Install the client's public certificate in to the server's keystore</h2>
 * <ul>
 * <li>keytool -exportcert -alias clientkey -file client-public.cer -keystore clientkeystore.jks -storepass password</li>
 * <li>keytool -importcert -keystore serverkeystore.jks -alias clientcert -file client-public.cer -storepass password -noprompt</li>
 * </ul>
 * <h2>Export the Server's Public Certificate and Import it in to the client's Keystore</h2>
 * <ul>
 * <li>keytool -exportcert -alias serverkey -file server-public.cer -keystore serverkeystore.jks -storepass password</li>
 * <li>keytool -importcert -keystore clientkeystore.jks -alias servercert -file server-public.cer -storepass password -noprompt</li>
 * </ul>
 * <h2>Generate a new alias serverkey1</h2>
 * <ul>
 * <li>keytool -genkeypair -alias serverkey1 -keyalg RSA -dname "CN=localhost,OU=Organization Unit,O=Organization,L=City,S=State,C=IN" -keypass password -keystore serverkeystore.jks -storepass password</li>
 * <li>Do not import the public certificate corresponding to serverkey1 into client's keystore.</li>
 * </ul>
 * <h2>Check keystore contents</h2>
 * <ul>
 * <li>keytool -list -keystore serverkeystore.jks -storepass password</li>
 * <li>keytool -list -v -keystore serverkeystore.jks -storepass password -alias serverkey1</li>
 * <li>keytool -list -v -keystore clientkeystore.jks -storepass password -alias clientkey</li>
 * <ul>
 * 
 * http://www.smartjava.org/content/how-analyze-java-ssl-errors
 */
package com.freemindcafe.socket.ssl.sample4;