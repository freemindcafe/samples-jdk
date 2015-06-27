/**
 * 
 */
package com.freemindcafe.apache.commons.beanutils.sample1;

import java.util.HashMap;
import java.util.Map;

public class DeviceConfigurationProfile {
	
	private Map<String,String> profileParams = new HashMap<String,String>();
	private String dataStoreType; //current or candidate
	private String name;
    private String xmlMessage;//external xml fragment
    
	public Map<String, String> getProfileParams() {
		return profileParams;
	}
	public void setProfileParams(Map<String, String> profileParams) {
		this.profileParams = profileParams;
	}
	public String getXmlMessage() {
		return xmlMessage;
	}
	public void setXmlMessage(String xmlMessage) {
		this.xmlMessage = xmlMessage;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDataStoreType() {
		return dataStoreType;
	}
	public void setDataStoreType(String dataStoreType) {
		this.dataStoreType = dataStoreType;
	}
}
