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

public class Accused implements Constants, QueryConst {

	public Accused(int complain_id, int accused_id, String accused_name,
			String accused_mobileno, String accused_address_block,
			String accused_pincode) {
		super();
		this.complain_id = complain_id;
		this.accused_id = accused_id;
		this.accused_name = accused_name;
		this.accused_mobileno = accused_mobileno;
		this.accused_address_block = accused_address_block;
		this.accused_pincode = accused_pincode;
	}

	public Accused() {
		super();
	}

	public String create() {
		PreparedStatement prepareStatement = null;
		ResultSet result = null;
		int res = 0;
		try {
			prepareStatement = DBConnection.connect().prepareStatement(
					INSERT_ACCUSED, Statement.RETURN_GENERATED_KEYS);
			prepareStatement.setInt(1, getComplain_id());
			prepareStatement.setString(2, getAccused_name());
			prepareStatement.setString(3, getAccused_mobileno());
			Address address = new Address(0, getAccused_address_block(),
					getAccused_pincode());
			address.create();
			int address_id = address.getAddress_id();
			prepareStatement.setInt(4, address_id);

			System.out.println(getAccused_name() + "" + getaccused_mobileno());

			res = prepareStatement.executeUpdate();

			result = prepareStatement.getGeneratedKeys();

			result.close();
			prepareStatement.close();
			DBConnection.connect().close();
		} catch (SQLException e) {
			e.printStackTrace();

		}
		if (res != 0)
			return SUCCESS;

		return FAILED;
	}

	public JSONArray retrieve() {
		PreparedStatement prepareStatement = null;
		ResultSet result = null;
		JSONObject json = null;
		JSONArray jarray = new JSONArray();
		int res = 0;
		try {

			prepareStatement = DBConnection.connect().prepareStatement(
					SELECT_ACCUSED);
			prepareStatement.setInt(1, getComplain_id());

			result = prepareStatement.executeQuery();

			while (result.next()) {
				try {

					json = new JSONObject();
					json.put(COMPLAINT_ID, result.getInt(1));
					json.put("accused_id", result.getInt(2));
					json.put(ACCUSED_NAME, result.getString(3));
					Address address = new Address();
					address.setAddress_id(result.getInt(5));
					json.put(ADDRESS_ID, address.select());
					json.put(ACCUSED_MOBILE_NO, result.getString(4));

					/*
					 * insert address details
					 */
					jarray.put(json);

				} catch (JSONException e) {

				}
			}
			result.close();
			prepareStatement.close();
			DBConnection.connect().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block

		}
		System.out.println(jarray);
		return jarray;
	}

	private int complain_id;
	private int accused_id;
	private String accused_name;
	private String accused_mobileno;
	private String accused_address_block;
	private String accused_pincode;

	public int getaccused_id() {
		return accused_id;
	}

	void setaccused_id(int accused_id) {
		this.accused_id = accused_id;
	}

	public String getaccused_name() {
		return accused_name;
	}

	public void setaccused_name(String accused_name) {
		this.accused_name = accused_name;
	}

	public String getaccused_mobileno() {
		return accused_mobileno;
	}

	public void setaccused_mobileno(String accused_mobileno) {
		this.accused_mobileno = accused_mobileno;
	}

	public int getComplain_id() {
		return complain_id;
	}

	public void setComplain_id(int complain_id) {
		this.complain_id = complain_id;
	}

	public int getAccused_id() {
		return accused_id;
	}

	public void setAccused_id(int accused_id) {
		this.accused_id = accused_id;
	}

	public String getAccused_name() {
		return accused_name;
	}

	public void setAccused_name(String accused_name) {
		this.accused_name = accused_name;
	}

	public String getAccused_mobileno() {
		return accused_mobileno;
	}

	public void setAccused_mobileno(String accused_mobileno) {
		this.accused_mobileno = accused_mobileno;
	}

	public String getAccused_address_block() {
		return accused_address_block;
	}

	public void setAccused_address_block(String accused_address_block) {
		this.accused_address_block = accused_address_block;
	}

	public String getAccused_pincode() {
		return accused_pincode;
	}

	public void setAccused_pincode(String accused_pincode) {
		this.accused_pincode = accused_pincode;
	}

}
