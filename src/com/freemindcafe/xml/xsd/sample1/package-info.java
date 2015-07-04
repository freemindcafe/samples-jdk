/**
 * \brief 'po.xsd' is in 'purchase'namsespace. Without elementFormDefault="qualified" all local elements are in default namespace and not in 
 * target namespace.
 * 
 * <p>elementFormDefault="qualified"</p>
 * <ul>
 * <li>Without this in 'po.xsd', a local element declaration (such as that for shipTo, billTo, items) refers to an element in no namespace, 
 * rather than an element in the target namespace</li>
 * </ul>
 * <h3>Test1</h3>
 * <ul>
 * <li>po.xml specifies every element in 'http://www.freemindcafe.com/purchase' namespace and hence validation fails.</li>
 * </ul>
 * <h3>Test1</h3> 
 * <ul>
 * <li>po-default.xml specifies every element in default namespace except purchaseOrder and comment (These being not the local element in po.xsd belongs
 * to targetNamespace.) and hence the test passes.<li>
 * </ul> 
 * 
 */
package com.freemindcafe.xml.xsd.sample1;