package com.freemindcafe.apache.cxf.jaxrs.sample8;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TicketsContainer {
	
	private List<Ticket> tickets;
	
	public void setTickets(List<Ticket> tickets){
		this.tickets = tickets;
	}

}
