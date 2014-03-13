package com.law.order.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.law.order.controller.utility.Constants;
import com.law.order.model.utility.DBConnection;
import com.law.order.model.utility.QueryConst;

public class SubDepartment implements QueryConst,Constants{


	public JSONObject retrive() {
		JSONObject json = new JSONObject();
		PreparedStatement prepareStatement = null;
		try {
			prepareStatement = DBConnection.connect().prepareStatement(
					SELECT_SUB_DEPT);
			prepareStatement.setInt(1, getSub_dept_id());
			prepareStatement.setInt(2, getDept_type_id());
			ResultSet result = prepareStatement.executeQuery();
			while (result.next()) {
				try {
					json.put(SUB_DEPT_ID, result.getString(1));
					json.put(SUB_DEPT_NAME, result.getString(2));

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
	
	
	
	/////////////////////////////////////////////////////////////////////////
	
	
	
	
	
	
	
	public int getSub_dept_id() {
		return sub_dept_id;
	}

	public void setSub_dept_id(int sub_dept_id) {
		this.sub_dept_id = sub_dept_id;
	}

	public int getDept_type_id() {
		return dept_type_id;
	}

	public void setDept_type_id(int dept_type_id) {
		this.dept_type_id = dept_type_id;
	}

	public String getSub_department_name() {
		return sub_department_name;
	}

	public void setSub_department_name(String sub_department_name) {
		this.sub_department_name = sub_department_name;
	}

	public SubDepartment(int sub_dept_id, int dept_type_id,
			String sub_department_name) {
		super();
		this.sub_dept_id = sub_dept_id;
		this.dept_type_id = dept_type_id;
		this.sub_department_name = sub_department_name;
	}

	
	public SubDepartment() {
		super();
	}

	private int sub_dept_id;
	private int dept_type_id;
	private String sub_department_name;
	
	
	
	
}
