/**
 * \brief  XMLBean samples.
 * 
 * <p> When we generate the code 
 * <ul>
 * <li>xmlbean takes a SHA-1 hash of the file and store the xsd information in xsb format under schemaorg_apache_xmlbeans/system/sha-1 directory 
 * in classpath</li>
 * <li>The schemaorg_apache_xmlbeans directory has element, namespace etc.</li>
 * <li>The generated code is lined to schemaorg_apache_xmlbeans/system/sha-1 binary information using a constant field in the source code.</li>
 * <li>There will be a single copy of the generated code and it will be linked with the latest sha-1 binary information.</li>
 * <li>By simply changing the namespace we can recreate the whole structure. What is the benefit??</li>
 * </ul>
 * <h3>Setup</h3>
 * Run build.xml under specific folders xml/xsd/sample1 etc to generate the corresponding code.
  */
package com.freemindcafe.xml.xsd;