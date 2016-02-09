package com.freemindcafe.apache.cxf.jaxrs.sample6;

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
	 	ticket1.setXml(IOUtils.toString(this.getClass().getResourceAsStream("ticket1.xml")));

		ticket2.setId(2L);
		ticket2.setAssignee("subhash");
	 	ticket2.setDescription("desc");
	 	ticket2.setXml(IOUtils.toString(this.getClass().getResourceAsStream("ticket2.xml")));
	 	
	 	tickets.put(1L, ticket1);
	 	tickets.put(2L, ticket2);

	}
	
	
 	@GET
 	@Path("/asContainer")
 	@Produces(MediaType.APPLICATION_JSON)
	public TicketsContainer getTicketsAsContainer(){
 		TicketsContainer tc = new TicketsContainer();
 		tc.setTickets(tickets.values().stream().collect(Collectors.toList()));
 		return tc;
	}
 	
 	@GET
 	@Path("/")
 	@Produces(MediaType.APPLICATION_JSON)
	public List<Ticket> getTickets(){
 		return tickets.values().stream().collect(Collectors.toList());
	} 
 	
 	@GET
 	@Path("/{ticketId}")
	public Ticket getTicket(@PathParam("ticketId") Long ticketId){
 		System.out.println(ticketId);
 		System.out.println(tickets.get(ticketId));
 		return tickets.get(ticketId);
	}
 	
 	@GET
 	@Path("/{ticketId}/asXml")
 	@Produces(MediaType.APPLICATION_XML)
	public Ticket getTicketAsXml(@PathParam("ticketId") Long ticketId){
 		System.out.println(ticketId);
 		System.out.println(tickets.get(ticketId));
 		return tickets.get(ticketId);
	}
 	
 	@GET
 	@Path("/{ticketId}/asJson")
 	@Produces(MediaType.APPLICATION_JSON)
	public Ticket getTicketAsJson(@PathParam("ticketId") Long ticketId){
 		System.out.println(ticketId);
 		System.out.println(tickets.get(ticketId));
 		return tickets.get(ticketId);
	} 	
 	

 	@GET
 	@Path("/{ticketId}/xml")
	public String getTicketXml(@PathParam("ticketId") Long ticketId){
 		System.out.println(ticketId);
 		System.out.println(tickets.get(ticketId).getXml());
 		return tickets.get(ticketId).getXml();
	} 
 	
 	@GET
 	@Path("/{ticketId}/xml/asXml")
 	@Produces(MediaType.APPLICATION_XML)
	public String getTicketXmlAsXml(@PathParam("ticketId") Long ticketId){
 		return tickets.get(ticketId).getXml();
	}
 	
 	@GET
 	@Path("/{ticketId}/xml/asJson")
 	@Produces(MediaType.APPLICATION_JSON)
	public String getTicketXmlAsJson(@PathParam("ticketId") Long ticketId){
 		return tickets.get(ticketId).getXml();
	}

}
