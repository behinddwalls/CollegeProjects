package com.law.order.controller;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.law.order.controller.utility.Constants;
import com.law.order.model.Address;
import com.law.order.model.Citizen;
import com.law.order.model.Complain;

public class CitizenController implements Constants {

	@GET
	@Path("/create")
	public Response createCitizen(@FormParam(value = "name") String name,
			@FormParam(value = "email_id") String email_id,
			@FormParam(value = "dob") String dob,
			@FormParam(value = "gender") String gender,
			@FormParam(value = "pincode") String pincode,
			@FormParam(value = "address_block") String address_block,
			@FormParam(value = "mobile") String mobile,
			@FormParam(value = "password") String password,
			@FormParam(value = "profilepic") String profilepic)
			throws JSONException {
		System.out.println("hiiiiiiiii");
		JSONObject response = new JSONObject();
		Address address = new Address(0, pincode, address_block);
		address.create();
		System.out.println("hiiiiiiiii");
		int address_id = address.getAddress_id();
		System.out.println(address_id);
		String uid = "";
		Citizen citizen = new Citizen(0, name, email_id, dob, gender,
				address_id, mobile, "", profilepic, password);
		if (citizen.create().equals(SUCCESS))
			{
			response.put("status", SUCCESS);
			response.put("uid", citizen.getUid());
		}
		else
			response.put("status", FAILED);
		System.out.println(response.toString());
		return Response.ok(response.toString()).build();
	}

	@GET
	@Path("/retrieve")
	public Response retrieveCitizen(@QueryParam(value = "uid") int uid) {

		Citizen citizen = new Citizen();
		citizen.setUid(uid);
		return Response.ok(citizen.retrive().toString()).build();
	}

	@GET
	@Path("/retrievecomplain")
	public Response retrieveComplain(@QueryParam(value = "uid") int uid) {

		Complain complain = new Complain();

		return Response.ok(complain.citizenComplain(uid)).build();
	}

	@GET
	@Path("/retrievespecificcomplain")
	public Response retrieveSpecificComplain(
			@QueryParam(value = "complain_id") int complain_id,
			@QueryParam(value = "citizen_id") int citizen_id) {

		Complain complain = new Complain();

		return Response.ok(
				complain.citizenSpecificComplain(complain_id, citizen_id))
				.build();
	}

	@GET
	@Path("/update")
	public Response updateCitizen(@FormParam(value = "uid") int uid,
			@FormParam(value = "name") String name,
			@FormParam(value = "email_id") String email_id,
			@FormParam(value = "dob") String dob,
			@FormParam(value = "gender") String gender,
			@FormParam(value = "pincode") String pincode,
			@FormParam(value = "address_block") String address_block,
			@FormParam(value = "mobile") String mobile,
			@FormParam(value = "password") String password,
			@FormParam(value = "profilepic") String profilepic,
			@FormParam(value = "address_id") int addressId)
			throws JSONException {

		JSONObject response = new JSONObject();
		Address address = new Address(addressId, pincode, address_block);
		address.update();
		int address_id = address.getAddress_id();
		Citizen citizen = new Citizen(uid, name, email_id, dob, gender,
				address_id, mobile, "", password, profilepic);
		
		if (citizen.update().equals(SUCCESS))
			response.put("status", SUCCESS);
		else
			response.put("status", FAILED);

		return Response.ok(response.toString()).build();
	}

	@DELETE
	@Path("/delete")
	public Response deleteCitizen(@FormParam(value = "uid") int uid) {

		JSONObject response = new JSONObject();
		Citizen citizen = new Citizen();
		citizen.setUid(uid);
		try {
			if (citizen.delete().equals(SUCCESS))
				response.put("status", SUCCESS);
			else
				response.put("status", FAILED);
		} catch (Exception e) {

		}
		return Response.ok(response.toString()).build();
	}

	@GET
	@Path("/createcomplain")
	public Response createComplain() {

		return Response.ok().build();
	}

	/*************************************************************/

	@GET
	@Path("/test")
	public Response test() {

		JSONObject ob = null;
		try {
			ob = new JSONObject("{\"name\":\"Preetam\"}");
		} catch (JSONException e) {
			// TODO Auto-generated catch block

		}

		return Response.ok(ob.toString()).build();
	}
}