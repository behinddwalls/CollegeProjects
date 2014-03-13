package com.law.order.controller;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.law.order.controller.utility.Constants;
import com.law.order.model.Accused;
import com.law.order.model.Address;
import com.law.order.model.Complain;
import com.law.order.model.PoliceOfficer;
import com.law.order.model.witness_table;

public class PoliceController implements Constants {

	private PoliceOfficer policeofficer = null;

	@GET
	@Path("/retrievepolice")
	public Response policeofficeretrieve(
			@QueryParam(value = "police_id") int policeId,
			@QueryParam(value = "dept_type") String deptTypeId,
			@QueryParam(value = "operation") String operation) {

		System.out.println(operation.equals("createvps"));
		if (operation.equals("createvps")) { // get all police where deptid is
			policeofficer = new PoliceOfficer();
			JSONObject obj = policeofficer.retrieveByDept();
			if (obj.has("content"))
				return Response.ok(obj.toString()).build();

		} else if (operation.equals("showpolice")) {

			policeofficer = new PoliceOfficer();
			policeofficer.setOfficer_id(policeId);
			JSONObject obj = policeofficer.retrive();
			return Response.ok(obj.toString()).build();
		}

		return Response.ok(FAILED).build();
	}

	@GET
	@Path("/createpolice")
	public Response policeofficercreate(
			@QueryParam(value = "email_id") String email_id,
			@QueryParam(value = "password") String password,
			@QueryParam(value = "officer_name") String officer_name,
			@QueryParam(value = "pincode") String pincode,
			@QueryParam(value = "address_block") String address_block,
			@QueryParam(value = "mobile_no") String mobile_no,
			@QueryParam(value = "id_copy") String identification_copy,
			@QueryParam(value = "gender") String gender,
			@QueryParam(value = "role") String role) throws JSONException {

		Address address = new Address(0, pincode, address_block);
		address.create();
		int address_id = address.getAddress_id();

		System.out.println(role + "  " + address_id);

		policeofficer = new PoliceOfficer(0, email_id, address_id, 0, 0,
				password, officer_name, mobile_no, identification_copy, gender);
		policeofficer.setRole(role);
		System.out.println(policeofficer.getRole());
		if (policeofficer.create().equals(SUCCESS))
			return Response.ok(SUCCESS).build();
		return Response.ok(FAILED).build();
	}

	@GET
	@Path("/retrievecomplain")
	public Response retrieveComplain(
			@QueryParam(value = "dept_id") int dept_id,
			@QueryParam(value = "vps_id") int vps_id) {

		Complain complain = new Complain();

		return Response.ok(
				complain.departmentComplain(dept_id, vps_id).toString())
				.build();
	}

	@GET
	@Path("/retrieveallcomplain")
	public Response retrieveallComplain(@QueryParam(value = "vps_id") int vps_id) {

		Complain complain = new Complain();

		return Response.ok(complain.allComplain(vps_id).toString()).build();
	}

	@GET
	@Path("updatepolice")
	public Response policeofficerupdate(@QueryParam(value = "id") int id,
			@QueryParam(value = "email_id") String email_id,
			@QueryParam(value = "password") String password,
			@QueryParam(value = "officer_name") String officer_name,
			@QueryParam(value = "pincode") String pincode,
			@QueryParam(value = "address_block") String address_block,
			@QueryParam(value = "address_id") int address_id,

			@QueryParam(value = "mobile_no") String mobile_no,
			@QueryParam(value = "id_copy") String identification_copy,
			@QueryParam(value = "gender") String gender)

	throws JSONException {

		Address address = new Address(address_id, pincode, address_block);
		address.update();

		policeofficer = new PoliceOfficer(id, email_id, address_id, 0, 0,
				password, officer_name, mobile_no, identification_copy, gender);

		if (policeofficer.update().equals(SUCCESS))
			Response.ok(SUCCESS).build();

		return Response.ok(FAILED).build();
	}

	@GET
	@Path("/retrievecomplainFIR")
	public Response retrieveComplainFIR(
			@QueryParam(value = "dept_id") int dept_id,
			@QueryParam(value = "vps_id") int vps_id) {

		Complain complain = new Complain();

		return Response.ok(
				complain.departmentComplainFIR(dept_id, vps_id).toString())
				.build();
	}

	@GET
	@Path("/retrievecomplainChargesheet")
	public Response retrieveComplainChargesheet(
			@QueryParam(value = "dept_id") int dept_id,
			@QueryParam(value = "vps_id") int vps_id) {

		Complain complain = new Complain();

		return Response.ok(
				complain.departmentComplainChargesheet(dept_id, vps_id)
						.toString()).build();
	}

	@GET
	@Path("/updatecomplain")
	public Response updateComplain(
			@QueryParam(value = "complainid") int complainId,
			@QueryParam(value = "complaintSub") String complaintSub,
			@QueryParam(value = "complaintDesc") String complaintDesc,
			@QueryParam(value = "dept") int dept,
			@QueryParam(value = "subDept") int subDept,
			@QueryParam(value = "assignedPolice") int assignedPolice,
			@QueryParam(value = "vps_id") int vps_id) {

		Complain complain = new Complain(complainId, 0, complaintDesc, "",
				dept, subDept, "", assignedPolice, complaintSub, vps_id);
		String res = complain.updateComplain();
		if (res.equals(SUCCESS))
			return Response.ok(SUCCESS).build();

		return Response.ok(FAILED).build();
	}

	@GET
	@Path("createaccused")
	public Response createAccused(
			@QueryParam("accusedname") String accusedName,
			@QueryParam(value = "gender") String gender,
			@QueryParam(value = "pincode") String pincode,
			@QueryParam(value = "address") String address,
			@QueryParam(value = "mobile") String mobile,
			@QueryParam(value = "complainid") int complain_id) {

		Accused accused = new Accused(complain_id, 0, accusedName, mobile,
				address, pincode);

		return Response.ok(accused.create()).build();
	}

	@GET
	@Path("createwitness")
	public Response createWitness(
			@QueryParam("witnaessname") String accusedName,
			@QueryParam(value = "gender") String gender,
			@QueryParam(value = "pincode") String pincode,
			@QueryParam(value = "address") String address,
			@QueryParam(value = "mobile") String mobile,
			@QueryParam(value = "complainid") int complain_id) {

		witness_table witness = new witness_table(0, accusedName, address,
				pincode, mobile, complain_id);

		return Response.ok(witness.create()).build();
	}

	@GET
	@Path("getclosedcomplain")
	public Response closedcomplain(@FormParam(value = "vps_id") int vps_id) {

		Complain complain = new Complain();

		return Response.ok(complain.getClosedcomplain(vps_id)).build();
	}

	@GET
	@Path("getallcomplain")
	public Response getAllcomplain(@FormParam(value = "dept_id") int dept_id) {

		Complain complain = new Complain();

		return Response.ok(complain.getALLcomplain(dept_id)).build();
	}

}
