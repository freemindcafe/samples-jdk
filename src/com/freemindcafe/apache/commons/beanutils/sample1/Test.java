package com.freemindcafe.apache.commons.beanutils.sample1;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.apache.commons.beanutils.PropertyUtils;

public class Test {
	
	@org.junit.Test
	public void it_is_easy_to_pull_the_indexed_properties() throws Exception{
		SetDeviceConfigurationRequest req = new SetDeviceConfigurationRequest();
		
		List<String> profiles = new ArrayList<String>(10);
		profiles.add("profile 1");
		profiles.add("profile 2");
		//req.setProfiles(profiles);

		List<DeviceConfigurationProfile> deviceConfigurationProfiles = new ArrayList<>();
		DeviceConfigurationProfile deviceConfigurationProfile = new DeviceConfigurationProfile();
		deviceConfigurationProfile.setXmlMessage("first xml message");
		deviceConfigurationProfiles.add(deviceConfigurationProfile);
		//req.setDeviceConfigurations(deviceConfigurationProfiles);
		
		Assert.assertTrue(PropertyUtils.getProperty(req, "profiles[0]").equals("profile 1"));
		Assert.assertTrue(PropertyUtils.getProperty(req, "profiles[1]").equals("profile 2"));
		Assert.assertTrue(PropertyUtils.getProperty(req, "deviceConfigurations[0].xmlMessage").equals("first xml message"));

	}
	
	@org.junit.Test
	public void can_we_set_the_indexed_property() throws Exception{
		SetDeviceConfigurationRequest req = new SetDeviceConfigurationRequest();
		req.setDeviceConfigurations(0, null);
		req.setDeviceConfigurations(1, null);
		
		PropertyUtils.setProperty(req, "profiles[0]", "profile 1");
		PropertyUtils.setProperty(req, "profiles[1]", "profile 2");	
		PropertyUtils.setProperty(req, "deviceConfigurations[0].xmlMessage", "First xml message");
		
		Assert.assertTrue(req.getProfiles().get(0).equalsIgnoreCase("profile 1"));
		Assert.assertTrue(req.getProfiles().get(1).equalsIgnoreCase("profile 2"));
		Assert.assertTrue(req.getDeviceConfigurations().get(0).getXmlMessage().equalsIgnoreCase("First xml messag"));
		
	}

}
