package com.freemindcafe.apache.cxf.jaxrs.sample4;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


public class TicketsResource {
	
 	@GET
 	@Produces(MediaType.APPLICATION_JSON)
	public List<Ticket> getTickets(){
 		Ticket ticket1 = new Ticket();
 		ticket1.setAssignee("nikhil");
 		ticket1.setDescription("desc");
 		List<Ticket> tickets = new ArrayList<Ticket>();
 		tickets.add(ticket1);
 		return tickets;
	}

}
