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

public class CriminalStatus implements Constants, QueryConst {

	/**
	 * Create Function
	 * */
	public String create() {
		int res = 0;
		PreparedStatement prepareStatement = null;
		ResultSet result = null;
		try {
			prepareStatement = DBConnection.connect().prepareStatement(
					INSERT_CRIMINAL_STATUS);
			prepareStatement.setInt(1, getCriminal_status_id());
			prepareStatement.setString(2, getCriminal_status_name());

			res = prepareStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
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
					SELECT_CRIMINAL_STATUS);
			prepareStatement.setInt(1, getCriminal_status_id());

			ResultSet result = prepareStatement.executeQuery();

			while (result.next()) {
				try {
					json.put(TRAFFIC_CHALLAN_ID, result.getString(1));
					json.put(TRAFFIC_TIME, result.getString(2));

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
					UPDATE_CRIMINAL_STATUS);
			prepareStatement.setString(1, getCriminal_status_name());

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
					DELETE_CRIMINAL_STATUS);
			prepareStatement.setInt(1, getCriminal_status_id());
			res = prepareStatement.executeUpdate();

		} catch (SQLException e) {
		}
		if (res != 0)
			return SUCCESS;

		return FAILED;
	}

	// ******************************************************************************
	public CriminalStatus() {
		super();
	}

	public CriminalStatus(int criminal_status_id, String criminal_status_name) {
		super();
		this.criminal_status_id = criminal_status_id;
		this.criminal_status_name = criminal_status_name;
	}

	// **********************************************************************************
	public int getCriminal_status_id() {
		return criminal_status_id;
	}

	public void setCriminal_status_id(int criminal_status_id) {
		this.criminal_status_id = criminal_status_id;
	}

	public String getCriminal_status_name() {
		return criminal_status_name;
	}

	public void setCriminal_status_name(String criminal_status_name) {
		this.criminal_status_name = criminal_status_name;
	}

	// **************************************************************************************
	private int criminal_status_id;
	private String criminal_status_name;

}
