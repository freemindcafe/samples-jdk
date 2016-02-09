package com.freemindcafe.apache.cxf.jaxrs.sample7;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.io.IOUtils;


public class TicketsResource {
	
	private Map<Long, Ticket> tickets = new HashMap<Long, Ticket>();
	
	public TicketsResource() throws Exception{
		Ticket ticket1 = new Ticket();
		Ticket ticket2 = new Ticket();
	 		
		ticket1.setId(1L);
		ticket1.setAssignee("nikhil");
	 	ticket1.setDescription("desc");

		ticket2.setId(2L);
		ticket2.setAssignee("subhash");
	 	ticket2.setDescription("desc");
	 	
	 	tickets.put(1L, ticket1);
	 	tickets.put(2L, ticket2);

	}
	
	
 	
 	@GET
 	@Path("/")
 	@Produces(MediaType.APPLICATION_JSON)
	public List<Ticket> getTickets(){
 		return tickets.values().stream().collect(Collectors.toList());
	} 
 	
}
