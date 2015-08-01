package com.freemindcafe.serialization.sample5;

import static com.freemindcafe.utils.FileSystemUtils.currentDir;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

import org.apache.activemq.util.JettisonMappedXmlDriver;

import com.freemindcafe.serialization.SerializationSvc;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import junit.framework.Assert;

public class Sample {
	
	@org.junit.Test
	public void test() {
		
		String filePath = currentDir()+"/src/com/freemindcafe/serialization/sample5"; 
		String fileName = "Employee.xml";
		BufferedReader br = null;
		final StringBuilder req = new StringBuilder();

		try {
			final File file = new File(filePath + "/" + fileName);
			// Get the object of DataInputStream
			final DataInputStream in = new DataInputStream(new FileInputStream(
					file.getAbsolutePath()));
			br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			// Read File Line By Line
			while ((strLine = br.readLine()) != null) {
				req.append(strLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		SerializationSvc serializationSvc = new SerializationSvc("/com/freemindcafe/serialization/sample5/XMLAliasing.xml",
				new Sample5SerializeXStream(new StaxDriver()), new Sample5SerializeXStream(new JettisonMappedXmlDriver()));
		Employee nikhilFromXml = (Employee)serializationSvc.fromXML(req.toString());
		Assert.assertNotNull(nikhilFromXml.someDate);
		
	}

}
