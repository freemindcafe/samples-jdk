package com.freemindcafe.apache.cxf.jaxrs.sample5;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


public class TicketsResource {
	
 	@GET
 	@Produces(MediaType.APPLICATION_JSON)
	public TicketsContainer getTickets(){
 		Ticket ticket1 = new Ticket();
 		ticket1.setAssignee("nikhil");
 		ticket1.setDescription("desc");
 		List<Ticket> tickets = new ArrayList<Ticket>();
 		tickets.add(ticket1);
 		TicketsContainer tc = new TicketsContainer();
 		tc.setTickets(tickets);
 		return tc;
	}

}
