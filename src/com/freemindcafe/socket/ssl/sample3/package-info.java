/**
 * Client and server share each others key+public certificate hence client auth succeeds.
 * 
 * <h1>Setup</h1>
 * <h2>Generate the Client and Server Keystores</h2>
 * <ul>
 * <li>keytool -genkeypair -alias serverkey -keyalg RSA -dname "CN=localhost,OU=Organization Unit,O=Organization,L=City,S=State,C=IN" -keypass password -keystore serverkeystore.jks -storepass password</li>
 * <li>keytool -genkeypair -alias clientkey -keyalg RSA -dname "CN=localhost,OU=Organization Unit,O=Organization,L=City,S=State,C=IN" -keypass password -storepass password -keystore clientkeystore.jks</li>
 * </ul>
 * <h2>Install the client's key in to the server's keystore</h2>
 * <ul>
 * <li>keytool -importkeystore -srckeystore clientkeystore.jks -destkeystore serverkeystore.jks -srcstoretype JKS -deststoretype JKS -srcstorepass password -deststorepass password -srcalias clientkey</li>
 * </ul>
 * <h2>Install the server's key in to the clients's keystore</h2>
 * <ul>
 * <li>keytool -importkeystore -srckeystore serverkeystore.jks -destkeystore clientkeystore.jks -srcstoretype JKS -deststoretype JKS -srcstorepass password -deststorepass password -srcalias serverkey</li>
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
package com.freemindcafe.socket.ssl.sample3;