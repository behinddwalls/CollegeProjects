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

public class Department implements Constants,QueryConst {

	
	private int department_type_id;
	private String department_name;
	
	
	public Department(int department_type_id, String department_name) {
		super();
		this.department_type_id = department_type_id;
		this.department_name = department_name;
	}
	
	public Department()
	{
		super();
	}
	
	
	public JSONObject retriveAll() {
		JSONObject json = null;
		JSONArray jArray = new JSONArray();
		JSONObject resp = new JSONObject();
		PreparedStatement prepareStatement = null;
		try {
			prepareStatement = DBConnection.connect().prepareStatement(
					SELECT_DEPARTMENT_ALL);

			ResultSet result = prepareStatement.executeQuery();
			while (result.next()) {
				try {
					json = new JSONObject();
					json.put("dept_type_id", result.getString(1));
					json.put("dept_name", result.getString(2));

				} catch (JSONException e) {
				}
				jArray.put(json);
			}
			resp.put("content", jArray);
			result.close();
			prepareStatement.close();
			DBConnection.connect().close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resp;
	}
	
	public JSONArray select() {

		JSONObject json = null;
		JSONArray jsonArray = new JSONArray();
		PreparedStatement prepareStatement = null;
		try {
			prepareStatement = DBConnection.connect().prepareStatement(
					SELECT_DEPARTMENT);
			prepareStatement.setInt(1, getDepartment_type_id());
			ResultSet result = prepareStatement.executeQuery();

			while (result.next()) {
				json = new JSONObject();
				json.put(DEPT_TYPE_ID, result.getString(1));
				json.put(DEPT_NAME, result.getString(2));
				
				jsonArray.put(json);
			}

		} catch (Exception e) {

		}
		return jsonArray;
	}

	public JSONObject retrive() {
		JSONObject json = new JSONObject();
		PreparedStatement prepareStatement = null;
		try {
			prepareStatement = DBConnection.connect().prepareStatement(
					SELECT_DEPARTMENT);
			prepareStatement.setInt(1, getDepartment_type_id());
			ResultSet result = prepareStatement.executeQuery();
			while (result.next()) {
				try {
					json.put("dept_type_id", result.getString(1));
					json.put("dept_name", result.getString(2));

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
	
	public int getDepartment_type_id() {
		return department_type_id;
	}
	public void setDepartment_type_id(int department_type_id) {
		this.department_type_id = department_type_id;
	}
	public String getDepartment_name() {
		return department_name;
	}
	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}
	
	
}
