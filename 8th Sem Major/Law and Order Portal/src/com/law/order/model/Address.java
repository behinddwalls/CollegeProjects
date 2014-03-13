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

public class Address implements QueryConst, Constants {

	public Address(int address_id, String pincode, String address_block) {
		super();
		this.address_id = address_id;
		this.pincode = pincode;
		this.address_block = address_block;
	}

	public String create() {
		PreparedStatement prepareStatement = null;
		ResultSet result = null;
		int res = 0;
		try {
			prepareStatement = DBConnection.connect().prepareStatement(
					INSERT_ADDRESS, Statement.RETURN_GENERATED_KEYS);

			prepareStatement.setString(1, getAddress_block());
			prepareStatement.setString(2, getPincode());
			res = prepareStatement.executeUpdate();

			result = prepareStatement.getGeneratedKeys();
			if (result.next()) {
				setAddress_id(result.getInt(1));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block

		}
		if (res != 0)
			return "Succes";

		return "Failed";
	}

	public String update() {
		PreparedStatement prepareStatement = null;
		ResultSet result = null;
		int res = 0;
		try {
			prepareStatement = DBConnection.connect().prepareStatement(
					UPDATE_ADDRESS);

			prepareStatement.setString(1, getPincode());
			prepareStatement.setString(2, getAddress_block());
			prepareStatement.setInt(3, getAddress_id());
			res = prepareStatement.executeUpdate();

			result = prepareStatement.getGeneratedKeys();
			if (result.next()) {
				setAddress_id(result.getInt(1));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block

		}
		if (res != 0)
			return "Succes";

		return "Failed";
	}

	public JSONArray select() {

		JSONObject json = null;
		JSONArray jsonArray = new JSONArray();
		PreparedStatement prepareStatement = null;
		try {
			prepareStatement = DBConnection.connect().prepareStatement(
					SELECT_ADDRESS);
			prepareStatement.setInt(1, getAddress_id());
			ResultSet result = prepareStatement.executeQuery();
			System.out.println(getAddress_id() + "hello");
			while (result.next()) {
				json = new JSONObject();
				json.put(ADDRESS_ID, result.getString(1));
				json.put(PINCODE, result.getString("pincode"));
				json.put(ADDRESS_BLOCK, result.getString("address_block"));
				jsonArray.put(json);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonArray;
	}

	public JSONObject retrieveAreaDetails() {
		PreparedStatement prepareStatement = null;
		ResultSet result = null;
		JSONObject json = new JSONObject();
		int res = 0;
		try {

			prepareStatement = DBConnection.connect().prepareStatement(
					SELECT_AREA_DETAILS);
			prepareStatement.setString(1, getPincode());

			result = prepareStatement.executeQuery();

			while (result.next()) {
				try {
					json.put(PINCODE, result.getString(1));
					json.put(AREA_NAME, result.getString(2));
					json.put(DISTRICT, result.getString(3));

				} catch (JSONException e) {

				}
			}
			result.close();
			prepareStatement.close();
			DBConnection.connect().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block

		}
		return json;
	}

	/******************************************************
	 * 
	 * 
	 * 
	 *******************************************************/
	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getAddress_block() {
		return address_block;
	}

	public void setAddress_block(String address_block) {
		this.address_block = address_block;
	}

	public void setAddress_id(int address_id) {
		this.address_id = address_id;
	}

	public int getAddress_id() {
		return address_id;
	}

	public Address() {
		super();
	}

	private int address_id;
	private String pincode;
	private String address_block;
}