package com.freemindcafe.xml.xsd.sample3;

import java.io.File;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;

import junit.framework.Assert;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;

import com.freemindcafe.purchase2.PurchaseOrderDocument;

public class Test {
	
	@org.junit.Test
	public void read_xml_and_validate_against_the_schema_fails() throws Exception{
		InputStream stream = this.getClass().getResourceAsStream("/com/freemindcafe/xml/xsd/sample3/po1.xml");
		PurchaseOrderDocument document = PurchaseOrderDocument.Factory.parse(stream);
		XmlOptions options = new XmlOptions();
		ArrayList<Object> validationErrors=new ArrayList<Object>();
		options.setErrorListener(validationErrors);
		if (!document.validate(options)) {
		    StringWriter sW=new StringWriter();
		    for (Object o : validationErrors) {
		      sW.append(o + "\n");
		    }
		    System.out.println(sW.toString());
		}
		Assert.assertFalse(document.validate());		
	}
	
	
	@org.junit.Test
	public void read_xml_and_validate_against_the_schema_succeeds() throws Exception{
		InputStream stream = this.getClass().getResourceAsStream("/com/freemindcafe/xml/xsd/sample3/po2.xml");
		PurchaseOrderDocument document = PurchaseOrderDocument.Factory.parse(stream);
		XmlOptions options = new XmlOptions();
		ArrayList<Object> validationErrors=new ArrayList<Object>();
		options.setErrorListener(validationErrors);
		if (!document.validate(options)) {
		    StringWriter sW=new StringWriter();
		    for (Object o : validationErrors) {
		      sW.append(o + "\n");
		    }
		    System.out.println(sW.toString());
		}
		Assert.assertTrue(document.validate());			
	}

}
