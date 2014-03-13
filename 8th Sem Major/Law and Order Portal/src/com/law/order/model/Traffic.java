package com.law.order.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.law.order.controller.utility.Constants;
import com.law.order.model.utility.DBConnection;
import com.law.order.model.utility.QueryConst;

public class Traffic implements Constants, QueryConst {

	/**
	 * Create Function
	 * */
	public String create() {
		int res = 0;
		PreparedStatement prepareStatement = null;
		ResultSet result = null;
		try {
			prepareStatement = DBConnection.connect().prepareStatement(
					INSERT_TRAFFIC, Statement.RETURN_GENERATED_KEYS);
			
			prepareStatement.setString(1, getTraffic_time());
			prepareStatement.setString(2, getTraffic_pincode());
			prepareStatement.setString(3, getTraffic_fine());
			prepareStatement.setInt(4, getTraffic_charge_id());
			prepareStatement.setString(5, getVehicle_no());
			prepareStatement.setString(6, getVehicle_owner_name());
			prepareStatement.setString(7, getMobile_no());
			prepareStatement.setInt(8, getTraffic_police_id());
			

			res = prepareStatement.executeUpdate();
			result = prepareStatement.getGeneratedKeys();
			if (result.next()) {

				setTraffic_challan_id(result.getInt(1));
			}

		} catch (SQLException e) {
		}
		if (res != 0)
			return SUCCESS;

		return FAILED;
	}

	/***
	 * 
	 * retrieve function
	 * */
	public JSONObject retrieve() {
		JSONObject json = new JSONObject();
		PreparedStatement prepareStatement = null;
		try {
			prepareStatement = DBConnection.connect().prepareStatement(
					SELECT_TRAFFIC);
			prepareStatement.setInt(1, getTraffic_challan_id());

			ResultSet result = prepareStatement.executeQuery();

			while (result.next()) {
				try {
					json.put(TRAFFIC_CHALLAN_ID, result.getString(1));
					json.put(TRAFFIC_TIME, result.getString(2));

					json.put(TRAFFIC_PINCODE, result.getString(3));
					json.put(TRAFFIC_FINE, result.getString(4));
					// charge
					TrafficCharge traffic_charge = new TrafficCharge();
					traffic_charge.setTraffic_charge_id(result.getInt(5));
					json.put(TRAFFIC_CHARGE_ID, new TrafficCharge().retrive());

					json.put(VEHICAL_NO, result.getString(6));
					json.put(VEHICAL_OWNER_NAME, result.getString(7));
					json.put(MOBILE_NO, result.getString(8));
					json.put(TRAFFIC_POLICE_ID, result.getString(9));

					Address address = new Address();
					address.setAddress_id(result.getInt(10));
					json.put(VEHICAL_OWNER_ADDRESS_ID, new Address().select());

				} catch (JSONException e) {
				}
			}
			result.close();
			prepareStatement.close();
			DBConnection.connect().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return json;
	}

	/***
	 * 
	 * update function
	 * */
	public String update() {
		int res = 0;
		PreparedStatement prepareStatement = null;
		try {
			prepareStatement = DBConnection.connect().prepareStatement(
					UPDATE_TRAFFIC);
			prepareStatement.setString(1, getTraffic_time());
			prepareStatement.setString(2, getTraffic_pincode());
			prepareStatement.setString(3, getTraffic_fine());
			prepareStatement.setInt(4, getTraffic_charge_id());
			prepareStatement.setString(5, getVehicle_no());
			prepareStatement.setString(6, getVehicle_owner_name());
			prepareStatement.setString(7, getMobile_no());
			prepareStatement.setInt(8, getTraffic_police_id());
			

			res = prepareStatement.executeUpdate();
		} catch (SQLException e) {
		}
		if (res != 0)
			return SUCCESS;

		return FAILED;
	}

	/***
	 * 
	 * DELETE function
	 * */
	public String delete() {
		int res = 0;
		PreparedStatement prepareStatement = null;
		try {
			prepareStatement = DBConnection.connect().prepareStatement(
					DELETE_TRAFFIC);
			prepareStatement.setInt(1, getTraffic_challan_id());
			res = prepareStatement.executeUpdate();

		} catch (SQLException e) {
		}
		if (res != 0)
			return SUCCESS;

		return FAILED;
	}

	/******************************************************
 * 
 * 
 * 
 *******************************************************/

	public Traffic() {
		super();
	}

	public Traffic(int traffic_challan_id, String traffic_time,
			String traffic_pincode, String traffic_fine,
			int traffic_charge_id, String vehicle_no,
			String vehicle_owner_name, String mobile_no,
			int traffic_police_id) {
		super();
		this.traffic_challan_id = traffic_challan_id;
		this.traffic_time = traffic_time;
		this.traffic_pincode = traffic_pincode;
		this.traffic_fine = traffic_fine;
		this.traffic_charge_id = traffic_charge_id;
		this.vehicle_no = vehicle_no;
		this.vehicle_owner_name = vehicle_owner_name;
		this.mobile_no = mobile_no;
		this.traffic_police_id = traffic_police_id;
		
	}

	// ************************************************************

	public String getTraffic_fine() {
		return traffic_fine;
	}

	public void setTraffic_fine(String traffic_fine) {
		this.traffic_fine = traffic_fine;
	}

	public String getTraffic_pincode() {
		return traffic_pincode;
	}

	public void setTraffic_pincode(String traffic_pincode) {
		this.traffic_pincode = traffic_pincode;
	}

	public int getTraffic_charge_id() {
		return traffic_charge_id;
	}

	public void setTraffic_charge_id(int traffic_charge_id) {
		this.traffic_charge_id = traffic_charge_id;
	}

	public String getVehicle_no() {
		return vehicle_no;
	}

	public void setVehicle_no(String vehicle_no) {
		this.vehicle_no = vehicle_no;
	}

	public String getVehicle_owner_name() {
		return vehicle_owner_name;
	}

	public void setVehicle_owner_name(String vehicle_owner_name) {
		this.vehicle_owner_name = vehicle_owner_name;
	}

	public String getMobile_no() {
		return mobile_no;
	}

	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}

	public int getTraffic_police_id() {
		return traffic_police_id;
	}

	public void setTraffic_police_id(int traffic_police_id) {
		this.traffic_police_id = traffic_police_id;
	}

	public String getTraffic_time() {
		return traffic_time;
	}

	public void setTraffic_time(String traffic_time) {
		this.traffic_time = traffic_time;
	}

	

	public int getTraffic_challan_id() {
		return traffic_challan_id;
	}

	public void setTraffic_challan_id(int traffic_challan_id) {
		this.traffic_challan_id = traffic_challan_id;
	}

	/******************************************************
		 * 
		 * 
		 * 
		 *******************************************************/
	private int traffic_challan_id;
	private String traffic_time;
	private String traffic_pincode;
	private String traffic_fine;
	private int traffic_charge_id;
	private String vehicle_no;
	private String vehicle_owner_name;
	private String mobile_no;
	private int traffic_police_id;
	

}
