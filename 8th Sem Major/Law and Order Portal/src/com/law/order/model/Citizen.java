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

public class Citizen implements Constants, QueryConst {

	public Citizen(int uid, String citizen_name, String email_id,
			String date_of_birth, String gender, int address_id,
			String mobile_number, String identification_copy, String password,
			String profilepic) {
		super();
		this.uid = uid;
		this.citizen_name = citizen_name;
		this.email_id = email_id;
		this.date_of_birth = date_of_birth;
		this.gender = gender;
		this.address_id = address_id;
		this.mobile_number = mobile_number;
		this.identification_copy = identification_copy;
		this.password = password;
		this.profilepic = profilepic;
	}

	/**
	 * Create Function
	 * */
	public String create() {
		int res = 0;
		PreparedStatement prepareStatement = null;
		try {
			prepareStatement = DBConnection.connect().prepareStatement(
					INSERT_CITIZEN , Statement.RETURN_GENERATED_KEYS);
			prepareStatement.setString(1, getCitizen_name());
			prepareStatement.setString(2, getMobile_number());
			prepareStatement.setString(3, getIdentification_copy());
			prepareStatement.setString(4, getEmail_id());
			prepareStatement.setString(5, getDate_of_birth());
			prepareStatement.setInt(6, getAddress_id());
			prepareStatement.setString(7, getPassword());
			prepareStatement.setString(8, getProfilepic());
			prepareStatement.setString(9, getGender());

			res = prepareStatement.executeUpdate();
			
			ResultSet result = prepareStatement.getGeneratedKeys();
			if(result.next())
			{
				setUid(result.getInt(1));
			}

		} catch (SQLException e) {
		}
		if (res != 0)
			return SUCCESS;

		return FAILED;
	}

	public JSONObject checkLogin() {

		int VAL = 0;
		JSONObject json = null;
		ResultSet result = null;
		PreparedStatement prepareStatement = null;
		try {
			prepareStatement = DBConnection.connect().prepareStatement(
					CHECK_CITIZEN);
			prepareStatement.setString(1, getEmail_id());
			prepareStatement.setString(2, getPassword());

			result = prepareStatement.executeQuery();

			if (result.next()) {
				VAL = result.getInt(1);
			}

			System.out.println(VAL);
			json = new JSONObject();
			try {
				json.put("pid", VAL);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (SQLException e) {
		}

		System.out.println(json);
		return json;

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
					SELECT_CITIZEN);
			prepareStatement.setInt(1, getUid());

			ResultSet result = prepareStatement.executeQuery();

			while (result.next()) {
				try {
					json.put(UID, result.getString(1));
					json.put(CITIZEN_NAME, result.getString(2));
					json.put(MOBILE_NO, result.getString(3));
					json.put(IDENTIFICATION_COPY, result.getString(4));
					json.put(EMAIL_ID, result.getString(5));
					json.put(DATE_OF_BIRTH, result.getString(6));
					Address address = new Address();
					address.setAddress_id(result.getInt(7));
					json.put(ADDRESS_ID, address.select());
					json.put(PASSWORD, result.getString(8));
					json.put(USER_PROFILE_PIC, result.getString(9));
					json.put(GENDER, result.getString(10));
					json.put("addr_id", result.getInt(7));

				} catch (JSONException e) {
					e.printStackTrace();
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

	public String update() {
		int res = 0;
		PreparedStatement prepareStatement = null;
		try {
			prepareStatement = DBConnection.connect().prepareStatement(
					UPDATE_CITIZEN);
			prepareStatement.setString(1, getCitizen_name());
			prepareStatement.setString(2, getEmail_id());
			prepareStatement.setString(3, getDate_of_birth());
			prepareStatement.setString(4, getGender());
			prepareStatement.setInt(5, getAddress_id());
			prepareStatement.setString(6, getMobile_number());
			prepareStatement.setString(7, getPassword());
			prepareStatement.setInt(8, getUid());
			res = prepareStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (res != 0)
			return SUCCESS;

		return FAILED;
	}

	public String delete() {
		int res = 0;
		PreparedStatement prepareStatement = null;
		try {
			prepareStatement = DBConnection.connect().prepareStatement(
					DELETE_CITIZEN);
			prepareStatement.setInt(1, getUid());
			res = prepareStatement.executeUpdate();

		} catch (SQLException e) {
		}
		if (res != 0)
			return SUCCESS;

		return FAILED;
	}

	/*****************************************
	 * 
	 * 
	 * 
	 * @return
	 */
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getCitizen_name() {
		return citizen_name;
	}

	public void setCitizen_name(String citizen_name) {
		this.citizen_name = citizen_name;
	}

	public String getEmail_id() {
		return email_id;
	}

	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}

	public String getDate_of_birth() {
		return date_of_birth;
	}

	public void setDate_of_birth(String date_of_birth) {
		this.date_of_birth = date_of_birth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAddress_id() {
		return address_id;
	}

	public void setAddress_id(int address_id) {
		this.address_id = address_id;
	}

	public String getMobile_number() {
		return mobile_number;
	}

	public void setMobile_number(String mobile_number) {
		this.mobile_number = mobile_number;
	}

	public String getIdentification_copy() {
		return identification_copy;
	}

	public void setIdentification_copy(String identification_copy) {
		this.identification_copy = identification_copy;
	}

	public String getProfilepic() {
		return profilepic;
	}

	public void setProfilepic(String profilepic) {
		this.profilepic = profilepic;
	}

	public Citizen() {
		super();
	}

	private int uid;
	private String citizen_name;
	private String email_id;
	private String date_of_birth;
	private String gender;
	private int address_id;
	private String mobile_number;
	private String identification_copy;
	private String password;
	private String profilepic;

}