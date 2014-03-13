package com.law.order.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.law.order.controller.utility.Constants;
import com.law.order.model.utility.DBConnection;
import com.law.order.model.utility.QueryConst;

public class TrafficCharge implements Constants, QueryConst {

	/**
	 * Create Function
	 * */
	public String create() {
		int res = 0;
		PreparedStatement prepareStatement = null;
		ResultSet result = null;
		try {
			prepareStatement = DBConnection.connect().prepareStatement(
					INSERT_TRAFFIC_CHARGE, Statement.RETURN_GENERATED_KEYS);
		
			prepareStatement.setString(1, getTraffic_charge_name());

			result = prepareStatement.executeQuery();
			
			if (result.next()) {

				setTraffic_charge_id(result.getInt(1));
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
	public JSONObject retrive() {
		JSONObject json = new JSONObject();
		PreparedStatement prepareStatement = null;
		try {
			prepareStatement = DBConnection.connect().prepareStatement(
					SELECT_TRAFFIC);
			prepareStatement.setInt(1, getTraffic_charge_id());

			ResultSet result = prepareStatement.executeQuery();

			while (result.next()) {
				try {
					json.put(TRAFFIC_CHARGE_ID, result.getString(1));
					json.put(TRAFFIC_CHARGE_NAME, result.getString(2));

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
					UPDATE_TRAFFIC_CHARGE);
			prepareStatement.setInt(1, getTraffic_charge_id());
			prepareStatement.setString(2, getTraffic_charge_name());

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
					DELETE_TRAFFIC_CHARGE);
			prepareStatement.setInt(1, getTraffic_charge_id());
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

	public TrafficCharge() {
		super();
	}

	public TrafficCharge(int traffic_charge_id, String traffic_charge_name) {
		super();
		this.traffic_charge_id = traffic_charge_id;
		this.traffic_charge_name = traffic_charge_name;

	}

	// ************************************************************

	public int getTraffic_charge_id() {
		return traffic_charge_id;
	}

	public void setTraffic_charge_id(int traffic_charge_id) {
		this.traffic_charge_id = traffic_charge_id;
	}

	public String getTraffic_charge_name() {
		return traffic_charge_name;
	}

	public void setTraffic_charge_name(String traffic_charge_name) {
		this.traffic_charge_name = traffic_charge_name;
	}

	/******************************************************
	 * variable
	 * 
	 *******************************************************/
	private int traffic_charge_id;
	private String traffic_charge_name;

}
