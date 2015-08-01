package com.freemindcafe.serialization.sample4;

import org.apache.activemq.util.JettisonMappedXmlDriver;

import com.freemindcafe.serialization.SerializationSvc;
import com.thoughtworks.xstream.io.xml.StaxDriver;

public class Sample {
	
	@org.junit.Test(expected = NullPointerException.class)
	public void hashmap_having_null_throws_null_pointer_exception() {
		Employee nikhil = new Employee();
		nikhil.randomMap.put("abc", null);		
		String xml = new SerializationSvc("/com/freemindcafe/serialization/sample4/XMLAliasing.xml").toXML(nikhil);
		System.out.println(xml);
	}
	
	@org.junit.Test
	public void hashmap_having_null_succeeds_by_handing_it_in_Sample4SerializeXStream() {
		Employee nikhil = new Employee();
		nikhil.randomMap.put("abc", null);
		SerializationSvc serializationSvc = new SerializationSvc("/com/freemindcafe/serialization/sample4/XMLAliasing.xml", 
				new Sample4SerializeXStream(new StaxDriver()), new Sample4SerializeXStream(new JettisonMappedXmlDriver()));
		String xml = serializationSvc.toXML(nikhil);
		System.out.println(xml);
	}	

}
