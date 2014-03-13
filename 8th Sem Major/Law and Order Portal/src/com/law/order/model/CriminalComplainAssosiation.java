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

public class CriminalComplainAssosiation implements Constants, QueryConst{
	
	
	
	
	
	
	/**
	 * Create Function
	 * */
	public String create() {
		int res = 0;
		PreparedStatement prepareStatement = null;
		ResultSet result = null;
		try {
			prepareStatement = DBConnection.connect().prepareStatement(
					INSERT_CRIMINAL_COMPLAIN_ASSOSIATION, Statement.RETURN_GENERATED_KEYS);
			prepareStatement.setInt(1, getComplaint_id());
			prepareStatement.setInt(2, getCriminal_status_id());
			

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
					SELECT_CRIMINAL_COMPLAIN_ASSOSIATION);
			prepareStatement.setInt(1, getCriminal_id());

			ResultSet result = prepareStatement.executeQuery();

			while (result.next()) {
				try {
					json.put(CRIMINAL_ID, result.getString(1));
					
					Complain complain = new Complain();
					complain.setComplaint_id(result.getInt(2));
					json.put(COMPLAIN_ID, new Complain().retrieve());
					 
					CriminalStatus criminalstatus=new CriminalStatus();
					criminalstatus.setCriminal_status_id(result.getInt(3));
					json.put(CRIMINAL_STATUS_ID, new CriminalStatus().retrive());
					
					
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
	public String update() {
		int res = 0;
		PreparedStatement prepareStatement = null;
		try {
			prepareStatement = DBConnection.connect().prepareStatement(
					UPDATE_CRIMINAL_COMPLAIN_ASSOSIATION);
			prepareStatement.setInt(1, getComplaint_id());
			prepareStatement.setInt(2, getCriminal_status_id());
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
					DELETE_CRIMINAL_COMPLAIN_ASSOSIATION);
			prepareStatement.setInt(1, getCriminal_id());
			res = prepareStatement.executeUpdate();

		} catch (SQLException e) {
		}
		if (res != 0)
			return SUCCESS;

		return FAILED;
	}

	
	
	
	//*******************************************************
	public CriminalComplainAssosiation()
	{
		super();
	}
    public CriminalComplainAssosiation(int criminal_id,
			int complaint_id, int criminal_status_id) {
		super();
		this.criminal_id = criminal_id;
		this.complaint_id = complaint_id;
		this.criminal_status_id = criminal_status_id;
	}
	//***********************************************
	public int getCriminal_id() {
		return criminal_id;
	}
	public void setCriminal_id(int criminal_id) {
		this.criminal_id = criminal_id;
	}
	public int getComplaint_id() {
		return complaint_id;
	}
	public void setComplaint_id(int complaint_id) {
		this.complaint_id = complaint_id;
	}
	public int getCriminal_status_id() {
		return criminal_status_id;
	}
	public void setCriminal_status_id(int criminal_status_id) {
		this.criminal_status_id = criminal_status_id;
	}
	//*********************************************************
	private int criminal_id;
    private int complaint_id;
    private int criminal_status_id;

}
