package com.law.order.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;



public class Sample {

	@GET
	@Path("/get")
	public Response get()
	{
		
		return Response.ok("gjhgsdj").build();
	}
}
