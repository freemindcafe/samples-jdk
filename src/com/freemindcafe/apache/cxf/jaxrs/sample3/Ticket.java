package com.freemindcafe.apache.cxf.jaxrs.sample3;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Ticket {
	
	private String description;
	private String assignee;
	
	public Ticket(String assignee, String description){
		this.assignee = assignee;
		this.description = description;
	}	
}
