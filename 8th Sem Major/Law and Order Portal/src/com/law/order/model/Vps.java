package com.law.order.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import com.law.order.controller.utility.Constants;
import com.law.order.model.utility.DBConnection;
import com.law.order.model.utility.QueryConst;

public class Vps implements Constants, QueryConst {

	private PreparedStatement prepareStatement = null;

	private int vps_id;
	private String vps_name;
	private int vps_admin_id;
	private String password;

	public Vps(int vps_id, String vps_name, int vps_admin_id, String password) {
		super();

		this.vps_id = vps_id;
		this.vps_name = vps_name;
		this.vps_admin_id = vps_admin_id;
		this.password = password;
	}

	public Vps() {
		super();
	}

	public String create() {
		ResultSet result = null;
		int res = 0;
		try {

			prepareStatement = DBConnection.connect().prepareStatement(
					INSERT_VPS, Statement.RETURN_GENERATED_KEYS);
			// automatic
			prepareStatement.setString(1, getVps_name());
			prepareStatement.setInt(2, getVps_admin());
			prepareStatement.setString(3, getPassword()); // automatic

			res = prepareStatement.executeUpdate();
			result = prepareStatement.getGeneratedKeys();
			if (result.next()) {
				setVps_id(result.getInt(1));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block

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
					SELECT_VPS);
			prepareStatement.setInt(1, getVps_id());
			ResultSet result = prepareStatement.executeQuery();

			while (result.next()) {
				json = new JSONObject();
				json.put(VPS_ID, result.getInt(VPS_ID));
				json.put(VPS_NAME, result.getString(VPS_NAME));
				json.put("vps_admin_id", result.getInt(vps_admin_id));
				jsonArray.put(json);
			}

		} catch (Exception e) {

		}
		return jsonArray;
	}

	public JSONArray selectAll() {

		JSONObject json = null;
		JSONArray jsonArray = new JSONArray();
		PreparedStatement prepareStatement = null;
		try {
			prepareStatement = DBConnection.connect().prepareStatement(
					SELECT_VPS_ALL);
			ResultSet result = prepareStatement.executeQuery();

			while (result.next()) {
				json = new JSONObject();
				json.put(VPS_ID, result.getInt(VPS_ID));
				json.put(VPS_NAME, result.getString(VPS_NAME));
				json.put("vps_admin_id", result.getInt("vps_admin_id"));
				jsonArray.put(json);
			}

			result.close();
			prepareStatement.close();

		} catch (Exception e) {

		}

		return jsonArray;
	}

	public String update() {
		int res = 0;
		try {

			prepareStatement = DBConnection.connect().prepareStatement(
					UPDATE_VPS);
			// automatic
			prepareStatement.setString(1, getVps_name());
			prepareStatement.setInt(2, getVps_admin());
			prepareStatement.setString(3, getPassword()); // automatic
			prepareStatement.setInt(4, getVps_id());

			res = prepareStatement.executeUpdate();
			// automatic
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

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

	public String getVps_name() {
		return vps_name;
	}

	public void setVps_name(String vps_name) {
		this.vps_name = vps_name;
	}

	public int getVps_admin() {
		return vps_admin_id;
	}

	public void setVps_admin(int vps_admin) {
		this.vps_admin_id = vps_admin;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
