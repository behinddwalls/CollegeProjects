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
import com.law.order.model.utility.RetrieveComplain;

public class SubAdminController implements Constants {
	
	

	@GET
	@Path("/create")
	public Response createComplain(@FormParam(value = "citizen_id") int citizen_id,
			@FormParam(value = "complaint_content") String complaint_content,
			@FormParam(value = "complaint_time") String complaint_time,
			@FormParam(value = "complaint_status_id") int complaint_status_id,
			@FormParam(value = "complaint_subject") String complaint_subject) throws JSONException {

		JSONObject response = new JSONObject();
		Complain complaint = new 
				Complain(0,citizen_id,complaint_content,complaint_time,0,0,"",0,complaint_subject,0);
		complaint.create();
		
		if (complaint.create().equals(SUCCESS))
			response.put("status", SUCCESS);
		else
			response.put("status", FAILED);

		return Response.ok(response.toString()).build();
	}

	@GET
	@Path("/retrieve")
	public Response retrieveComplain(@QueryParam(value = "complaint_id") String complaint_id) {

		RetrieveComplain rcomplain=new RetrieveComplain();
		rcomplain.setComplaint_id(complaint_id);
		return Response.ok(rcomplain.select().toString()).build();
	}

	@GET
	@Path("/update")
	public Response updateComplain(@FormParam(value = "complaint_id") int complaint_id,@FormParam(value = "citizen_id") int citizen_id,
			@FormParam(value = "complaint_content") String complaint_content,
			@FormParam(value = "complaint_time") String complaint_time,
			@FormParam(value = "complaint_status_id") int complaint_status_id,
			@FormParam(value = "department_type_id") int department_type_id,
			@FormParam(value = "sub_department_id") int sub_department_id,
			@FormParam(value = "assigned_police_report") String assigned_police_report,
			@FormParam(value = "assigned_police_id") int assigned_ploice_id,
			@FormParam(value = "complaint_subject") String complaint_subject,@FormParam(value = "vps_id") int vps_id) throws JSONException {

		JSONObject response = new JSONObject();
		Complain complaint = new 
				Complain(complaint_id,citizen_id,complaint_content,complaint_time,department_type_id,sub_department_id,assigned_police_report,assigned_ploice_id,complaint_subject,vps_id);
		complaint.create();
		
		if (complaint.create().equals(SUCCESS))
			response.put("status", SUCCESS);
		else
			response.put("status", FAILED);

		return Response.ok(response.toString()).build();
	}

	@DELETE
	@Path("/delete")
	public Response deleteComplain(@FormParam(value = "complaint_id") int complaint_id) {

		JSONObject response = new JSONObject();
		Complain complain=new Complain();
			complain.setComplaint_id(complaint_id);
		try {
			if (complain
					.delete().equals(SUCCESS))
				response.put("status", SUCCESS);
			else
				response.put("status", FAILED);
		} catch (Exception e) {

		}
		return Response.ok(response.toString()).build();
	}


}
