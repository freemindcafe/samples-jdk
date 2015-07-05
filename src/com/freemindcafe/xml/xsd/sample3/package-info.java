/**
 * \brief  xsd:anyType can contain any payload. Schema validation check samples.
 * 
 * <p>With elementFormDefault="qualified" on po.xsd the elements are qualified and they belong to default namespace.</p>
 * <h3>Test1</h3>
 * <ul>
 * <li>new.xsd defines a new type that will be carried over po.xsd. new.xsd tries to use the old 'purchase2' namespace.</li>
 * <li>This new type should be under 'element' element as specified by po.xsd</li>
 * <li>po1.xml does not define it under 'element' element and hence first test fails.</li>
 * </ul>
 * <h3>Test2</h3>
 * <ul>
 * <li>po2.xml defines new element under 'element'.</li>
 * <li>And test case succeeds.</li>
 * </ul>
 * 
 * 
 */
package com.freemindcafe.xml.xsd.sample3;