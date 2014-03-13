package com.law.order.controller;

import javax.servlet.http.HttpSession;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import com.law.order.model.Citizen;
import com.law.order.model.PoliceOfficer;

public class LoginController {

	@POST
	@Path("/retrieve")
	public Response retrieveLogin(@FormParam(value = "role") String role,
			@FormParam(value = "email_id") String email_id,
			@FormParam(value = "password") String password) {
		System.out.println(role + "  " + email_id + " " + password);

		if (role.equals("Admin")||role.equals("police")||role.equals("SubAdmin")) {


			PoliceOfficer police = new PoliceOfficer();

			police.setEmail_id(email_id);
			police.setPassword(password);
			police.setRole(role);

			System.out.println(police.checkLogin());
			return Response.ok(police.checkLogin()).build();

		} else if (role.equals("traffic")) {


			PoliceOfficer police = new PoliceOfficer();

			police.setEmail_id(email_id);
			police.setPassword(password);
			police.setRole(role);

			System.out.println("it is clear "+police.checktrafficLogin());
			return Response.ok(police.checktrafficLogin()).build();

		}else {

			Citizen citizen = new Citizen();
			citizen.setEmail_id(email_id);
			citizen.setPassword(password);
			// citizen.setRole(role);
System.out.println(citizen.checkLogin());
			return Response.ok(citizen.checkLogin()).build();
		}

	}

}
