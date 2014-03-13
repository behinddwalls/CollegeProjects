package com.law.order.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.law.order.controller.utility.Constants;
import com.law.order.model.Address;
import com.law.order.model.Department;
import com.law.order.model.PoliceOfficer;
import com.law.order.model.utility.QueryConst;
import com.law.order.model.utility.RetrieveComplain;

public class DepartmentController implements Constants,QueryConst {

	
	
	@GET
	@Path(value = "/retrieve")
	public Response retrieve(@QueryParam(value = "all") String all)
			throws JSONException {

		Department dept = new Department();
		if (all.equals("all")) {
			JSONObject json = dept.retriveAll();
			if (json.has("content")) {
				return Response.ok(json.toString(10)).build();

			}
		} else {
		}

		return Response.ok(FAILED).build();
	}
	
	
	
}
