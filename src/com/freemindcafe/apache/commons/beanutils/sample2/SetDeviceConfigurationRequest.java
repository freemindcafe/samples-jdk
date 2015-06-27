/**
 * 
 */
package com.freemindcafe.apache.commons.beanutils.sample2;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author ic033961
 *
 */
public class SetDeviceConfigurationRequest extends MeterControlRequest{
	
	//meterControlRequest.deviceConfigurations = wfRequest.payload.abc
	//meterControlRequest.deviceConfigurations.entry.property1 = wfRequest.payload.abc.entry.property2
	//meterControlRequest.deviceConfigurations[0].
	
	private List<String> profiles = new ArrayList<>(10);
	private List<DeviceConfigurationProfile> deviceConfigurations=new ArrayList<>(10);
    private List<DeviceInterface> deviceInterfaces=new ArrayList<>();
    private Date snapShotTime;
    private Date activationDateTime;
	/**
	 * @return the deviceConfigurations
	 */
	public List<DeviceConfigurationProfile> getDeviceConfigurations() {
		return deviceConfigurations;
	}
	/**
	 * @param deviceConfigurations the deviceConfigurations to set
	 */
	public void setDeviceConfigurations(
			List<DeviceConfigurationProfile> deviceConfigurations) {
		this.deviceConfigurations = deviceConfigurations;
	}
	/**
	 * @return the deviceInterfaces
	 */
	public List<DeviceInterface> getDeviceInterfaces() {
		return deviceInterfaces;
	}
	/**
	 * @param deviceInterfaces the deviceInterfaces to set
	 */
	public void setDeviceInterfaces(List<DeviceInterface> deviceInterfaces) {
		this.deviceInterfaces = deviceInterfaces;
	}
	/**
	 * @return the snapShotTime
	 */
	public Date getSnapShotTime() {
		return snapShotTime;
	}
	/**
	 * @param snapShotTime the snapShotTime to set
	 */
	public void setSnapShotTime(Date snapShotTime) {
		this.snapShotTime = snapShotTime;
	}
	/**
	 * @return the activationDateTime
	 */
	public Date getActivationDateTime() {
		return activationDateTime;
	}
	/**
	 * @param activationDateTime the activationDateTime to set
	 */
	public void setActivationDateTime(Date activationDateTime) {
		this.activationDateTime = activationDateTime;
	}
	public List<String> getProfiles() {
		return profiles;
	}
	public void setProfiles(List<String> something) {
		this.profiles = something;
	}
}
