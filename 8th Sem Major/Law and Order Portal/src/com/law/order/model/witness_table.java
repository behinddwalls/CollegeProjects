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

public class witness_table implements Constants, QueryConst {

	public witness_table(int witness_id, String witness_name,
			String witness_address_block, String witness_pincode,
			String witness_mobileno, int complain_id) {
		super();
		this.witness_id = witness_id;
		this.witness_name = witness_name;
		this.witness_address_block = witness_address_block;
		this.witness_pincode = witness_pincode;
		this.witness_mobileno = witness_mobileno;
		this.complain_id = complain_id;
	}

	public witness_table() {
		super();
	}

	private int witness_id;
	private String witness_name;
	private String witness_address_block;
	private String witness_pincode;

	private String witness_mobileno;
	private int complain_id;

	public String create() {
		PreparedStatement prepareStatement = null;
		ResultSet result = null;
		int res = 0;
		try {
			prepareStatement = DBConnection.connect().prepareStatement(
					INSERT_WITNESS, Statement.RETURN_GENERATED_KEYS);
			prepareStatement.setInt(1, getComplain_id());
			prepareStatement.setString(2, getWitness_name());
			Address address = new Address(0, getWitness_address_block(),
					getWitness_pincode());
			address.create();
			int address_id = address.getAddress_id();
			prepareStatement.setInt(3, address_id);
			prepareStatement.setString(4, getWitness_mobileno());

			System.out.println(getWitness_name()+"  "+getWitness_mobileno());
			
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
					SELECT_WITNESS);
			prepareStatement.setInt(1, getComplain_id());

			result = prepareStatement.executeQuery();

			while (result.next()) {
				try {

					json = new JSONObject();
					json.put(COMPLAINT_ID, result.getInt(1));
					json.put("witness_id", result.getInt(2));
					json.put(WITNESS_NAME, result.getString(3));
					Address address = new Address();
					address.setAddress_id(result.getInt(4));
					json.put(ADDRESS_ID, address.select());
					
					json.put(WITNESS_MOBILE_NO, result.getString(5));

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

	public int getWitness_id() {
		return witness_id;
	}

	public void setWitness_id(int witness_id) {
		this.witness_id = witness_id;
	}

	public String getWitness_name() {
		return witness_name;
	}

	public void setWitness_name(String witness_name) {
		this.witness_name = witness_name;
	}

	public String getWitness_mobileno() {
		return witness_mobileno;
	}

	public void setWitness_mobileno(String witness_mobileno) {
		this.witness_mobileno = witness_mobileno;
	}

	public int getComplain_id() {
		return complain_id;
	}

	public void setComplain_id(int complain_id) {
		this.complain_id = complain_id;
	}

	public String getWitness_address_block() {
		return witness_address_block;
	}

	public void setWitness_address_block(String witness_address_block) {
		this.witness_address_block = witness_address_block;
	}

	public String getWitness_pincode() {
		return witness_pincode;
	}

	public void setWitness_pincode(String witness_pincode) {
		this.witness_pincode = witness_pincode;
	}

}
