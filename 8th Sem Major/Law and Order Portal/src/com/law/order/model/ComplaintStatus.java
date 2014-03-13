package com.law.order.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.law.order.controller.utility.Constants;
import com.law.order.model.utility.DBConnection;
import com.law.order.model.utility.QueryConst;

public class ComplaintStatus implements QueryConst, Constants {

	public ComplaintStatus(int complaint_id, String complaint_status_name) {
		super();
		this.complaint_id = complaint_id;
		this.complaint_status_name = complaint_status_name;
	}

	public JSONObject retrive() {
		JSONObject json = new JSONObject();
		PreparedStatement prepareStatement = null;
		try {
			prepareStatement = DBConnection.connect().prepareStatement(
					SELECT_COMPLAIN_STATUS);
			prepareStatement.setInt(1, getComplaint_id());
			ResultSet result = prepareStatement.executeQuery();
			while (result.next()) {
				try {
					json.put(COMPLAINT_STATUS_ID, result.getString(1));
					json.put(COMPLAINT_STATUS_NAME, result.getString(2));
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

	public String select() {

		String status = null;
		PreparedStatement prepareStatement = null;
		try {

			prepareStatement = DBConnection.connect().prepareStatement(
					SELECT_COMPLAIN_STATUS);
			prepareStatement.setInt(1, getComplaint_id());
			ResultSet result = prepareStatement.executeQuery();

			while (result.next()) {

				status = result.getString(2);

			}

		} catch (Exception e) {

		}
		return status;
	}

	public String update() {

		int res = 0;
		String status = null;
		PreparedStatement prepareStatement = null;
		try {

			prepareStatement = DBConnection.connect().prepareStatement(
					UPDATE_COMPLAIN_STATUS);
			prepareStatement.setInt(2, getComplaint_id());
			prepareStatement.setString(1, getComplaint_status_name());
			res = prepareStatement.executeUpdate();

		} catch (Exception e) {

		}
		if (res != 0)
			return SUCCESS;
		else
			return FAILED;
	}

	public String create() {
		int res = 0;
		String status = null;
		PreparedStatement prepareStatement = null;
		try {

			prepareStatement = DBConnection.connect().prepareStatement(
					INSERT_COMPLAIN_STATUS);
			prepareStatement.setInt(1, getComplaint_id());
			prepareStatement.setString(2, getComplaint_status_name());
			res = prepareStatement.executeUpdate();

		} catch (Exception e) {

		}
		if (res != 0)
			return SUCCESS;
		else
			return FAILED;
	}

	// /////////////////////////////////////////////////////////////

	public String getComplaint_status_name() {
		return complaint_status_name;
	}

	public void setComplaint_status_name(String complaint_status_name) {
		this.complaint_status_name = complaint_status_name;
	}

	public ComplaintStatus() {
		super();
	}

	private int complaint_id;
	private String complaint_status_name;

	public int getComplaint_id() {
		return complaint_id;
	}

	public void setComplaint_id(int complaint_id) {
		this.complaint_id = complaint_id;
	}

}
