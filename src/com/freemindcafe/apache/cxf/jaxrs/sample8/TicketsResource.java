package com.freemindcafe.apache.cxf.jaxrs.sample8;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.commons.io.IOUtils;


public class TicketsResource {
	
	private Map<Long, Ticket> tickets = new ConcurrentHashMap<Long, Ticket>();
	
	public TicketsResource() throws Exception{
		Ticket ticket1 = new Ticket();
		Ticket ticket2 = new Ticket();
	 		
		ticket1.setId(1L);
		ticket1.setAssignee("nikhil");
	 	ticket1.setDescription("desc");
	 	ticket1.setFileName("ticket1.xml");

		ticket2.setId(2L);
		ticket2.setAssignee("subhash");
	 	ticket2.setDescription("desc");
	 	ticket2.setFileName("ticket2.xml");
	 	
	 	tickets.put(1L, ticket1);
	 	tickets.put(2L, ticket2);

	}
	
	
 	
 	@GET
 	@Path("/")
 	@Produces(MediaType.APPLICATION_JSON)
	public List<Ticket> getTickets(){
 		System.out.println(tickets);
 		return tickets.values().stream().collect(Collectors.toList());
	}
 	
 	@GET
 	@Path("/assignees")
 	@Produces(MediaType.APPLICATION_JSON)
	public List<String> getAssignees(){
 		return tickets.values().stream().map(t->t.getAssignee()).collect(Collectors.toList());
	}  	
 	
 	@GET
 	@Path("/{ticketId}")
	public Ticket getTicket(@PathParam("ticketId") Long ticketId){
 		return tickets.get(ticketId);
	}
 	
 	@PUT
 	@Path("/{ticketId}")
 	@Consumes(MediaType.APPLICATION_JSON)
 	@Produces(MediaType.APPLICATION_JSON) 	
	public Ticket putTicket(@PathParam("ticketId") Long ticketId, Ticket ticket){
 		tickets.get(ticketId).setAssignee(ticket.getAssignee());
 		tickets.get(ticketId).setDescription(ticket.getDescription());
 		return tickets.get(ticketId);
	} 
 	
 	@POST
 	@Path("/")
 	@Consumes(MediaType.APPLICATION_JSON)
 	@Produces(MediaType.APPLICATION_JSON) 	
	public Ticket postTicket(Ticket ticket){
 		ticket.setId(tickets.size()+1L);
 		tickets.put(ticket.getId(), ticket);
 		return ticket;
	}
 	
 	@DELETE
 	@Path("/{ticketId}")
	public Ticket deleteTicket(@PathParam("ticketId") Long ticketId){
 		Ticket ticket = tickets.get(ticketId);
 		tickets.remove(ticketId);
 		return ticket;
	}
 	
 	@GET
 	@Path("/{ticketId}/download")
 	public Response downloadContent(@PathParam("ticketId")final Long ticketId) throws IOException{
 		Ticket ticket = tickets.get(ticketId);
 		ResponseBuilder responseBuilder = Response.ok(this.getClass().getResourceAsStream(ticket.getFileName()));
 		responseBuilder.type(MediaType.APPLICATION_XML);
 		responseBuilder.header("Content-Disposition", "attachment; filename=\""+ticket.getFileName()+"\"");
 		return responseBuilder.build();
	} 	
 	
// 	@PUT
// 	@Path("/{ticketId}/abc")
// 	@Consumes(MediaType.APPLICATION_JSON)
// 	@Produces(MediaType.APPLICATION_JSON) 	
//	public Ticket oneMorePutTicket(@PathParam("ticketId") Long ticketId, String assignee){
// 		tickets.get(ticketId).setAssignee(assignee);
// 		return tickets.get(ticketId);
//	} 
// 	
// 	@POST
// 	@Path("/xyz")
// 	@Consumes(MediaType.APPLICATION_JSON)
// 	@Produces(MediaType.APPLICATION_JSON) 	
//	public Ticket oneMorePostTicket(Ticket ticket){
// 		ticket.setId(tickets.size()+1L);
// 		tickets.put(ticket.getId(), ticket);
// 		return ticket;
//	}
 	
 	
 	
}
