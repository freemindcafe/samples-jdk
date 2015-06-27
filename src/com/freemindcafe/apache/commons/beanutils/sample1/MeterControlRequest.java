package com.freemindcafe.apache.commons.beanutils.sample1;

import java.util.Date;


public class MeterControlRequest{
	private Long svcPtId;
	private String deviceId;
	private Date windowStartDateTime;
	private Date windowEndDateTime;
	public Long getSvcPtId() {
		return svcPtId;
	}
	public void setSvcPtId(Long svcPtId) {
		this.svcPtId = svcPtId;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public Date getWindowStartDateTime() {
		return windowStartDateTime;
	}
	public void setWindowStartDateTime(Date windowStartDateTime) {
		this.windowStartDateTime = windowStartDateTime;
	}
	public Date getWindowEndDateTime() {
		return windowEndDateTime;
	}
	public void setWindowEndDateTime(Date windowEndDateTime) {
		this.windowEndDateTime = windowEndDateTime;
	}
}
