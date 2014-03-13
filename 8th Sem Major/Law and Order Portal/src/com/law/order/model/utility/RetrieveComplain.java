package com.law.order.model.utility;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.law.order.controller.utility.Constants;
import com.law.order.model.Citizen;
import com.law.order.model.ComplaintStatus;
import com.law.order.model.Department;
import com.law.order.model.SubDepartment;

public class RetrieveComplain implements Constants,QueryConst{

	public RetrieveComplain(String vps_id, String vps_department_type_id,
			String complaint_id) {
		super();
		this.vps_id = vps_id;
		this.vps_department_type_id = vps_department_type_id;
		this.complaint_id = complaint_id;
	}
	
	public RetrieveComplain()
	{
		super();
	}

	public String retrieve() {
		PreparedStatement prepareStatement = null;
		ResultSet result = null;
		JSONObject json = new JSONObject();
		int res = 0;
		try {
			if (!getComplaint_id().isEmpty() || getComplaint_id() != null) {
				prepareStatement = DBConnection.connect().prepareStatement(
						RETRIEVE_COMPLAINT);
				prepareStatement.setString(1, getVps_id());
				prepareStatement.setString(2,getVps_department_type_id());
				prepareStatement.setString(3,getComplaint_id());
  
			} else {
				prepareStatement = DBConnection.connect().prepareStatement(
						RETRIEVE_COMPLAINT_ALL);
				prepareStatement.setString(1,getVps_id());
				prepareStatement.setString(2,getVps_department_type_id());
				
				

			}
			result = prepareStatement.executeQuery();
			
			while (result.next()) {
				try {
					json.put(COMPLAINT_ID, result.getString(1));
					
				} catch (JSONException e) {

				}
			}
			result.close();
			prepareStatement.close();
			DBConnection.connect().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block

		}
		if (res != 0)
			return SUCCESS;

		return FAILED;
	}


	public String update() {
		PreparedStatement prepareStatement = null;
		ResultSet result = null;
		JSONObject json = new JSONObject();
		int res = 0;
		try {
			
				prepareStatement = DBConnection.connect().prepareStatement(
						UPDATE_COMPLAINT);
				prepareStatement.setString(1, getVps_id());
				prepareStatement.setString(2,getVps_department_type_id());
				prepareStatement.setString(3,getComplaint_id());
  
			result = prepareStatement.executeQuery();
			
			while (result.next()) {
				try {
					json.put(COMPLAINT_ID, result.getString(1));
					
				} catch (JSONException e) {

				}
			}
			result.close();
			prepareStatement.close();
			DBConnection.connect().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block

		}
		if (res != 0)
			return SUCCESS;

		return FAILED;
	}

	
	
	public String select() {
		PreparedStatement prepareStatement = null;
		ResultSet result = null;
		JSONObject json = new JSONObject();
		int res = 0;
		try {
			if (!getComplaint_id().isEmpty() || getComplaint_id() != null) {
				prepareStatement = DBConnection.connect().prepareStatement(
						SELECT_COMPLAINT);
				prepareStatement.setString(1, getVps_id());
				prepareStatement.setString(3,getComplaint_id());
  
			} else {
				prepareStatement = DBConnection.connect().prepareStatement(
						SELECT_COMPLAINT_ALL);
				prepareStatement.setString(1,getVps_id());
				
				
				

			}
			result = prepareStatement.executeQuery();
			
			while (result.next()) {
				try {
					json.put(COMPLAINT_ID, result.getString(1));
					
				} catch (JSONException e) {

				}
			}
			result.close();
			prepareStatement.close();
			DBConnection.connect().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block

		}
		if (res != 0)
			return SUCCESS;

		return FAILED;
	}

	
	
	
	private String vps_id;
	private String vps_department_type_id;
	private String complaint_id;
	
	public String getVps_id() {
		return vps_id;
	}
	
	
	public void setVps_id(String vps_id) {
		this.vps_id = vps_id;
	}
	public String getVps_department_type_id() {
		return vps_department_type_id;
	}
	public void setVps_department_type_id(String vps_department_type_id) {
		this.vps_department_type_id = vps_department_type_id;
	}
	public String getComplaint_id() {
		return complaint_id;
	}
	public void setComplaint_id(String complaint_id) {
		this.complaint_id = complaint_id;
	}
	
	
	
	
	
	
	
}

