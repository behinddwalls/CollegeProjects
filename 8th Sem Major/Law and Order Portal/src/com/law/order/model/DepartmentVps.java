package com.law.order.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.law.order.model.utility.DBConnection;

public class DepartmentVps {

	
	
	public DepartmentVps(int complaint_id, int sub_dept_id,
			int crime_location_id, String crime_time) {
		super();
		this.complaint_id = complaint_id;
		this.sub_dept_id = sub_dept_id;
		this.crime_location_id = crime_location_id;
		this.crime_time = crime_time;
	}
	public DepartmentVps()
	{
		super();
	}
	
	
	
	
	private int complaint_id;
	private int sub_dept_id;
	
	private int crime_location_id;
	private String crime_time;
	
	
	
	public int getComplaint_id() {
		return complaint_id;
	}
	public void setComplaint_id(int complaint_id) {
		this.complaint_id = complaint_id;
	}
	public int getSub_dept_id() {
		return sub_dept_id;
	}
	public void setSub_dept_id(int sub_dept_id) {
		this.sub_dept_id = sub_dept_id;
	}

	public int getCrime_location_id() {
		return crime_location_id;
	}
	public void setCrime_location_id(int crime_location_id) {
		this.crime_location_id = crime_location_id;
	}
	public String getCrime_time() {
		return crime_time;
	}
	public void setCrime_time(String crime_time) {
		this.crime_time = crime_time;
	}
	
	
}


