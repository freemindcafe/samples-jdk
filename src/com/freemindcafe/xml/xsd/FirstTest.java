package com.freemindcafe.xml.xsd;

import java.io.InputStream;
import java.net.URL;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stax.StAXSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.junit.Test;
import org.w3c.dom.Document;

public class FirstTest {

	@Test
	public void test() throws Exception {
		
		URL urlXSD = FirstTest.class.getResource("DeviceConfiguration.xsd");
		URL urlXML = FirstTest.class.getResource("DeviceConfiguration.xml");
		
	    StreamSource XSD = new StreamSource(urlXSD.openStream());
	    StreamSource XML = new StreamSource(urlXML.openStream());

		// Get the DOM Builder Factory
		//DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		// Get the DOM Builder
		//DocumentBuilder builder = factory.newDocumentBuilder();
		// Load and Parse the XML document
		//Document document = builder.parse(FirstTest.class.getResourceAsStream("DeviceConfiguration.xml"));
		
		
		SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI); 
		Schema schema = sf.newSchema(XSD);
		
		XMLStreamReader reader = XMLInputFactory.newFactory().createXMLStreamReader(XML);
		
		Validator validator = schema.newValidator();
		validator.setErrorHandler(new MyErrorHandler(reader));
		validator.validate(new StAXSource(reader));
		
		System.out.println("done");
		

	}

}
