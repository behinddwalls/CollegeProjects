package com.law.order.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.law.order.controller.CriminalController.Day;
import com.law.order.controller.utility.Constants;
import com.law.order.model.utility.DBConnection;
import com.law.order.model.utility.QueryConst;

public class CriminalRecord implements Constants, QueryConst {

	/**
	 * Create Function
	 * */
	public String create() {
		int res = 0;
		PreparedStatement prepareStatement = null;
		ResultSet result = null;
		try {
			prepareStatement = DBConnection.connect().prepareStatement(
					INSERT_CRIMINAL_RECORD, Statement.RETURN_GENERATED_KEYS);
			prepareStatement.setString(1, getCriminal_name());
			prepareStatement.setString(2, getHeight());
			prepareStatement.setString(3, getWeight());
			prepareStatement.setString(4, getComplextion());
			prepareStatement.setString(5, getIdentification_marks());
			prepareStatement.setString(6, getImage_url());
			prepareStatement.setString(7, getDate_of_birth());

			res = prepareStatement.executeUpdate();
			result = prepareStatement.getGeneratedKeys();
			if (result.next()) {

				setCriminal_id(result.getInt(1));
			}

		} catch (SQLException e) {
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
					SELECT_CRIMINAL_RECORD);
			prepareStatement.setInt(1, getCriminal_id());

			ResultSet result = prepareStatement.executeQuery();

			while (result.next()) {
				try {
					json.put(CRIMINAL_ID, result.getString(1));
					json.put(CRIMINAL_NAME, result.getString(2));
					json.put(HEIGHT, result.getString(3));
					json.put(WEIGHT, result.getString(4));
					json.put(COMPLEXION, result.getString(5));
					json.put(IDENTIFICATION_MARKS, result.getString(6));
					json.put(IMAGE_URL, result.getString(7));
					json.put(DATE_OF_BIRTH, result.getString(8));

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

	public enum Day {
		criminal_name, height, weight, identification_marks, complexion

	}

	Day day;

	public JSONArray select(String filterAttr, String filterVal) {
		JSONObject json = null;
		JSONArray jArray = new JSONArray();
		String VAL = null;
		ResultSet result = null;
		PreparedStatement prepareStatement = null;
		try {

			switch (day.valueOf(filterAttr)) {

			case criminal_name:
				VAL = criminal_name;
				break;
			case height:
				VAL = HEIGHT;
				break;
			case weight:
				VAL = WEIGHT;
				break;
			case complexion:
				VAL = COMPLEXION;
				break;
			case identification_marks:
				VAL = IDENTIFICATION_MARKS;
				break;

			}
			prepareStatement = DBConnection.connect().prepareStatement(
					"select *  from  criminal_record where " + VAL + "=?");

			prepareStatement.setString(1, filterVal);

			result = prepareStatement.executeQuery();
			try {
				while (result.next()) {

					json = new JSONObject();

					json.put(CRIMINAL_ID, result.getString(1));
					json.put(CRIMINAL_NAME, result.getString(2));

					json.put(HEIGHT, result.getString(3));
					json.put(WEIGHT, result.getString(4));

					json.put(COMPLEXION, result.getString(5));
					json.put(IDENTIFICATION_MARKS, result.getString(6));
					json.put(IMAGE_URL, result.getString(7));
					json.put(DATE_OF_BIRTH, result.getString(8));

					jArray.put(json);

				}

			} catch (JSONException e) {
			}
			result.close();
			prepareStatement.close();
			DBConnection.connect().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println(jArray);
		return jArray;
	}

	public String update() {
		int res = 0;
		PreparedStatement prepareStatement = null;
		try {
			prepareStatement = DBConnection.connect().prepareStatement(
					UPDATE_CRIMINAL_RECORD);
			prepareStatement.setString(1, getCriminal_name());
			prepareStatement.setString(2, getHeight());
			prepareStatement.setString(3, getWeight());
			prepareStatement.setString(4, getComplextion());
			prepareStatement.setString(5, getIdentification_marks());
			prepareStatement.setString(6, getImage_url());
			prepareStatement.setString(7, getDate_of_birth());

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
					DELETE_CRIMINAL_RECORD);
			prepareStatement.setInt(1, getCriminal_id());
			res = prepareStatement.executeUpdate();

		} catch (SQLException e) {
		}
		if (res != 0)
			return SUCCESS;

		return FAILED;
	}

	/******************************************************
 * 
 * 
 * 
 *******************************************************/
	public CriminalRecord() {
		super();
	}

	public CriminalRecord(int criminal_id, String criminal_name, String height,
			String weight, String complextion, String identification_marks,
			String image_url, String date_of_birth) {
		super();
		this.criminal_id = criminal_id;
		this.criminal_name = criminal_name;
		this.height = height;
		this.weight = weight;
		this.complextion = complextion;
		this.identification_marks = identification_marks;
		this.image_url = image_url;
		this.date_of_birth = date_of_birth;
	}

	public int getCriminal_id() {
		return criminal_id;
	}

	public void setCriminal_id(int criminal_id) {
		this.criminal_id = criminal_id;
	}

	public String getCriminal_name() {
		return criminal_name;
	}

	public void setCriminal_name(String criminal_name) {
		this.criminal_name = criminal_name;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getComplextion() {
		return complextion;
	}

	public void setComplextion(String complextion) {
		this.complextion = complextion;
	}

	public String getIdentification_marks() {
		return identification_marks;
	}

	public void setIdentification_marks(String identification_marks) {
		this.identification_marks = identification_marks;
	}

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

	public String getDate_of_birth() {
		return date_of_birth;
	}

	public void setDate_of_birth(String date_of_birth) {
		this.date_of_birth = date_of_birth;
	}

	// **********************************************************
	private int criminal_id;
	private String criminal_name;
	private String height;
	private String weight;
	private String complextion;
	private String identification_marks;
	private String image_url;
	private String date_of_birth;
}
