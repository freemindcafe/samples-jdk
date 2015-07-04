package com.freemindcafe.xml.xsd.sample4;

import java.io.File;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;

import junit.framework.Assert;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;

import com.freemindcafe.purchase3.gift.MessageType;
import com.freemindcafe.purchase3.PurchaseOrderDocument;

public class Test {
	
	@org.junit.Test
	public void read_xml_and_validate_against_the_schema_succeeds() throws Exception{
		InputStream stream = this.getClass().getResourceAsStream("/com/freemindcafe/xml/xsd/sample4/po.xml");
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
		XmlObject xmlObject = document.getPurchaseOrder().getElement();
		MessageType messageType = (MessageType)xmlObject.changeType(MessageType.type);
		System.out.println(messageType.isSetComment1());
		System.out.println(messageType.getGiftWrap());
		//Assert.assertTrue(messageType.getGiftWrap());
		System.out.println(messageType.getComment1());
	}

}
