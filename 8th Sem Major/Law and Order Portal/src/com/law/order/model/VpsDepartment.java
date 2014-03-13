package com.law.order.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import com.law.order.controller.utility.Constants;
import com.law.order.model.utility.DBConnection;
import com.law.order.model.utility.QueryConst;

public class VpsDepartment implements Constants, QueryConst {

	private PreparedStatement prepareStatement = null;
	private int vps_id;
	private int vps_department_type_id;

	public VpsDepartment(int vps_id, int vps_department_type_id) {
		super();

		this.vps_id = vps_id;
		this.vps_department_type_id = vps_department_type_id;
	}

	public VpsDepartment() {
		super();
	}

	public String create() {
		int res = 0;
		try {
			prepareStatement = DBConnection.connect().prepareStatement(
					INSERT_VPS_DEPARTMENT);
			prepareStatement.setInt(1, getVps_id()); // automatic
			prepareStatement.setInt(2, getvps_department_type_id());

			res = prepareStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		}
		if (res != 0)
			return SUCCESS;

		return FAILED;
	}

	public JSONArray select() {

		JSONObject json = null;
		JSONArray jsonArray = new JSONArray();
		PreparedStatement prepareStatement = null;
		try {
			prepareStatement = DBConnection.connect().prepareStatement(
					SELECT_VPS_DEPARTMENT);
			prepareStatement.setInt(1, getVps_id());
			ResultSet result = prepareStatement.executeQuery();

			while (result.next()) {
				json = new JSONObject();
				json.put(VPS_ID, result.getInt(1));
				json.put(VPS_DEPARTMENT_ID, result.getInt(2));

				jsonArray.put(json);
			}

		} catch (Exception e) {

		}
		return jsonArray;
	}

	public String delete(int id) {
		int res = 0;
		try {
			prepareStatement = DBConnection.connect().prepareStatement(
					DELETE_VPS_DEPARTMENT);
			// automatic

			prepareStatement.setInt(1, id);

			res = prepareStatement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block

		}
		if (res != 0)
			return SUCCESS;

		return FAILED;
	}

	public int getVps_id() {
		return vps_id;
	}

	public void setVps_id(int vps_id) {
		this.vps_id = vps_id;
	}

	public int getvps_department_type_id() {
		return vps_department_type_id;
	}

	public void setvps_department_type_id(int vps_department_type_id) {
		this.vps_department_type_id = vps_department_type_id;
	}

}
