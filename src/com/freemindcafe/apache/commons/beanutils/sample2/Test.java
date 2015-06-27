package com.freemindcafe.apache.commons.beanutils.sample2;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

public class Test {
	
	@org.junit.Test
	public void first() throws Exception{
		SetDeviceConfigurationRequest req = new SetDeviceConfigurationRequest();
		List<String> profiles = new ArrayList<String>(10);
		profiles.add("profile 1");
		profiles.add("profile 2");
		req.setProfiles(profiles);

		List<DeviceConfigurationProfile> deviceProfiles = new ArrayList<>();
		DeviceConfigurationProfile deviceProfile = new DeviceConfigurationProfile();
		deviceProfile.setXmlMessage("first xml message");
		deviceProfiles.add(deviceProfile);
		req.setDeviceConfigurations(deviceProfiles);
		
		System.out.println(PropertyUtils.getProperty(req, "profiles[0]"));
		System.out.println(PropertyUtils.getProperty(req, "profiles[1]"));
		
		System.out.println(PropertyUtils.getProperty(req, "deviceConfigurations[0].xmlMessage"));
		//System.out.println(PropertyUtils.getProperty(req, "profiles[1]"));		
		
		System.exit(0);
		
		Map<String, Object> properties = new HashMap<>();
		properties.put("svcPtId", "1");
		properties.put("deviceId", "12");
		properties.put("windowStartDateTime", new Date());
		//properties.put("windowEndDateTime", "2015-06-26");
		properties.put("activationDateTime", new Date());
		properties.put("something[0]", "x");
		//properties.put("something[1]", "y");
		//properties.put("something[1]", "y");
		//properties.put("deviceConfigurations[0].xmlMessage", "my message");
		
		try {
			BeanUtils.populate(req, properties);
			System.out.println(BeanUtils.describe(req));
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			e.printStackTrace();
		}

		System.out.println(req.getDeviceConfigurations().get(0).getXmlMessage());
		System.out.println("hi");
	}
	
	@org.junit.Test
	public void test2() throws Exception{
		SetDeviceConfigurationRequest req = new SetDeviceConfigurationRequest();
		
		PropertyUtils.setProperty(req, "profiles[0]", "profile 1");
		PropertyUtils.setProperty(req, "profiles[1]", "profile 2");	
		PropertyUtils.setProperty(req, "deviceConfigurations[0].xmlMessage", "First xml message");
		
		Assert.assertTrue(req.getProfiles().get(0).equalsIgnoreCase("profile 1"));
		Assert.assertTrue(req.getProfiles().get(1).equalsIgnoreCase("profile 2"));
		Assert.assertTrue(req.getDeviceConfigurations().get(0).getXmlMessage().equalsIgnoreCase("First xml messag"));
		
		System.exit(0);

	}

}
