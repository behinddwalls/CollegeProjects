package com.law.order.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.law.order.controller.utility.Constants;
import com.law.order.model.Accused;
import com.law.order.model.Citizen;
import com.law.order.model.CriminalRecord;
import com.law.order.model.witness_table;
import com.law.order.model.utility.QueryConst;

public class CriminalController implements Constants, QueryConst {

	public enum Day {
		criminal_name, height, weight, identification_marks, complexion

	}

	Day day;

	@GET
	@Path("/retrieve")
	public Response retrieveCriminal(
			@QueryParam(value = "filter") String filterAttr,
			@QueryParam(value = "searchKey") String filterVal) {

		CriminalRecord criminalrecord = new CriminalRecord();

		switch (day.valueOf(filterAttr)) {

		case criminal_name:
			return Response.ok(criminalrecord.select(CRIMINAL_NAME, filterVal))
					.build();

		case height:
			return Response.ok(criminalrecord.select(HEIGHT, filterVal))
					.build();

		case weight:
			return Response.ok(criminalrecord.select(WEIGHT, filterVal))
					.build();

		case complexion:
			return Response.ok(criminalrecord.select(COMPLEXION, filterVal))
					.build();

		case identification_marks:
			return Response.ok(
					criminalrecord.select(IDENTIFICATION_MARKS, filterVal))
					.build();

		}

		return Response.ok(FAILED).build();
	}

	@GET
	@Path("/retrieveaccused")
	public Response retrieveAccused(
			@QueryParam(value = "complain_id") int complain_id) {

		Accused accused = new Accused();

		accused.setComplain_id(complain_id);
		return Response.ok(accused.retrieve()).build();

	}
	
	@GET
	@Path("/retrievewitness")
	public Response retrieveWitness(
			@QueryParam(value = "complain_id") int complain_id) {

		witness_table witness= new witness_table();

		witness.setComplain_id(complain_id);
		return Response.ok(witness.retrieve()).build();

	}

}
