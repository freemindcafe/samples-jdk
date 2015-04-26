/**
 * Client and server do not share public certificates, hence client auth fails.
 * 
 * <h1>Setup</h1>
 * <h2>Generate the Client and Server Keystores</h2>
 * <ul>
 * <li>keytool -genkeypair -alias serverkey -keyalg RSA -dname "CN=localhost,OU=Organization Unit,O=Organization,L=City,S=State,C=IN" -keypass password -keystore serverkeystore.jks -storepass password</li>
 * <li>keytool -genkeypair -alias clientkey -keyalg RSA -dname "CN=localhost,OU=Organization Unit,O=Organization,L=City,S=State,C=IN" -keypass password -storepass password -keystore clientkeystore.jks</li>
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
package com.freemindcafe.socket.ssl.sample1;