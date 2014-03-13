package com.law.order.controller;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.law.order.controller.utility.Constants;
import com.law.order.model.Address;
import com.law.order.model.Traffic;
import com.law.order.model.TrafficCharge;
import com.law.order.model.utility.QueryConst;

public class TrafficController implements Constants,QueryConst {

	@POST
	@Path("/createTraffic")
	public String create(
			@FormParam(value = "traffic_time") String traffic_time,
			@FormParam(value = "traffic_pincode") String traffic_pincode,
			@FormParam(value = "traffic_fine") String traffic_fine,
			@FormParam(value = "vehicle_no") String vehicle_no,
			@FormParam(value = "vehicle_owner_name") String vehicle_owner_name,
			@FormParam(value = "mobile_no") String mobile_no,
			
			@FormParam(value = "traffic_police_id") int traffic_police_id,
			@FormParam(value = "traffic_charge_name") String traffic_charge_name)
			throws JSONException {
		
		

		JSONObject response = new JSONObject();
		
		int traffic_challan_id = 0;
		int traffic_charge_id = 0;

		TrafficCharge traffic_charge = new TrafficCharge(0,
				traffic_charge_name);
		traffic_charge.create();
		traffic_charge_id = traffic_charge.getTraffic_charge_id();
		
		Traffic traffic = new Traffic(traffic_challan_id, traffic_time,
				traffic_pincode, traffic_fine, traffic_charge_id, vehicle_no,
				vehicle_owner_name, mobile_no, traffic_police_id);

		if (traffic.create().equals(SUCCESS))
			return SUCCESS;
		else
			return FAILED;

		
	}

	@POST
	@Path("/updateTraffic")
	public Response update(
			@FormParam(value = "traffic_challan_id") int traffic_challan_id,
			@FormParam(value = "traffic_time") String traffic_time,
			@FormParam(value = "traffic_pincode") String traffic_pincode,
			@FormParam(value = "traffic_fine") String traffic_fine,
			@FormParam(value = "vehicle_no") String vehicle_no,
			
			@FormParam(value = "vehicle_owner_name") String vehicle_owner_name,
			@FormParam(value = "mobile_no") String mobile_no,
			@FormParam(value = "address_id") int address_id,
			@FormParam(value = "address_block") String address_block,
			@FormParam(value = "pincode") String pincode,
			@FormParam(value = "traffic_police_id") int traffic_police_id,
			@FormParam(value = "traffic_charge_id") int traffic_charge_id,
			@FormParam(value = "traffic_charge_name") String traffic_charge_name)
			throws JSONException {

		JSONObject response = new JSONObject();
		Address address = new Address(address_id, pincode, address_block);
		address.setAddress_id(address_id);
		address.update();
		TrafficCharge traffic_charge = new TrafficCharge(traffic_charge_id,
				traffic_charge_name);
		traffic_charge.setTraffic_charge_id(traffic_charge_id);
		traffic_charge.update();

		Traffic traffic = new Traffic(traffic_challan_id, traffic_time,
				traffic_pincode, traffic_fine, traffic_charge_id, vehicle_no,
				vehicle_owner_name, mobile_no, traffic_police_id);
		
		traffic.setTraffic_challan_id(traffic_challan_id);
		traffic.update();

		if (address.equals(SUCCESS))
			response.put("status", SUCCESS);
		else
			response.put("status", FAILED);

		return Response.ok(response.toString()).build();
	}

	@POST
	@Path("/retrieve")
	public Response retrieve(
			@QueryParam(value = "traffic_challan_id") int traffic_challan_id) {

		Traffic traffic = new Traffic();
		traffic.setTraffic_challan_id(traffic_challan_id);
		return Response.ok(traffic.retrieve().toString()).build();
	}

	@DELETE
	@Path("/delete")
	public Response delete(
			@FormParam(value = "traffic_challan_id") int traffic_challan_id) {

		JSONObject response = new JSONObject();
		Traffic traffic = new Traffic();

		traffic.setTraffic_challan_id(traffic_challan_id);
		try {
			if (traffic.delete().equals(SUCCESS))
				response.put("status", SUCCESS);
			else
				response.put("status", FAILED);
		} catch (Exception e) {

		}
		return Response.ok(response.toString()).build();
	}

}
