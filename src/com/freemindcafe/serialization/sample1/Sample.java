package com.freemindcafe.serialization.sample1;

import java.util.Date;

import com.freemindcafe.serialization.SerializationSvc;

import junit.framework.Assert;

public class Sample {
	
	@org.junit.Test
	public void test() {
		Employee nikhil = new Employee();
		nikhil.joiningDate = new Date();
		nikhil.employeeId = 1L;
		nikhil.team = "Apps";

		Employee shiv = new Employee();
		shiv.joiningDate = new Date();
		shiv.employeeId = 2L;
		shiv.team = "Apps";
		
		Employee vikas = new Employee();
		vikas.joiningDate = new Date();
		vikas.employeeId = 3L;
		vikas.team = "Apps";
		
		nikhil.reportingManager = shiv;
		nikhil.functionalManager = vikas;
		
		String xml = SerializationSvc.getSerializationSvc().toXML(nikhil);
		String json = SerializationSvc.getSerializationSvc().toJSON(nikhil);
		
		System.out.println(xml);
		System.out.println(json);
		
		Employee nikhilFromXml = (Employee)SerializationSvc.getSerializationSvc().fromXML(xml);
		Employee nikhilFromJson = (Employee)SerializationSvc.getSerializationSvc().fromJSON(json);
		
		Assert.assertTrue(nikhilFromXml.joiningDate.equals(nikhil.joiningDate));
		Assert.assertTrue(nikhilFromXml.employeeId == nikhil.employeeId);
		Assert.assertTrue(nikhilFromXml.functionalManager.employeeId == nikhil.functionalManager.employeeId);
		
		Assert.assertTrue(nikhilFromJson.joiningDate.equals(nikhil.joiningDate));
		Assert.assertTrue(nikhilFromJson.employeeId == nikhil.employeeId);
		Assert.assertTrue(nikhilFromJson.functionalManager.employeeId == nikhil.functionalManager.employeeId);
	}

}
