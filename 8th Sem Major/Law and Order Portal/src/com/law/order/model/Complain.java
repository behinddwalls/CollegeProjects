package com.law.order.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.law.order.controller.utility.Constants;
import com.law.order.model.utility.DBConnection;
import com.law.order.model.utility.QueryConst;

public class Complain implements Constants, QueryConst {

	public Complain(int complaint_id, int citizen_id, String complaint_content,
			String complaint_time, int dept_type_id, int sub_dept_type_id,
			String assigned_police_report, int assigned_police_id,
			String complain_subject, int vps_id) {
		super();
		this.complaint_id = complaint_id;
		this.citizen_id = citizen_id;
		this.complaint_content = complaint_content;
		this.complaint_time = complaint_time;
		this.dept_type_id = dept_type_id;
		this.sub_dept_type_id = sub_dept_type_id;
		this.assigned_police_report = assigned_police_report;
		this.assigned_police_id = assigned_police_id;
		this.complain_subject = complain_subject;
		this.vps_id = vps_id;
	}

	public String updateComplain() {
		PreparedStatement prepareStatement = null;
		ResultSet result = null;
		int res = 0;
		try {
			prepareStatement = DBConnection.connect().prepareStatement(
					UPDATE_COMPLAIN);
			prepareStatement.setString(1, getComplaint_content());
			prepareStatement.setInt(2, getDept_type_id());
			prepareStatement.setInt(3, getSub_dept_type_id());
			prepareStatement.setInt(4, getAssigned_police_id());
			prepareStatement.setString(5, getComplain_subject());
			prepareStatement.setInt(6, getComplaint_id());

			res = prepareStatement.executeUpdate();

			prepareStatement.close();
			DBConnection.connect().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block

		}
		if (res != 0)
			return SUCCESS;

		return FAILED;
	}

	public String create() {
		PreparedStatement prepareStatement = null;
		ResultSet result = null;
		int res = 0;
		try {
			prepareStatement = DBConnection.connect().prepareStatement(
					INSERT_COMPLAINT, Statement.RETURN_GENERATED_KEYS);
			prepareStatement.setInt(1, getCitizen_id());
			prepareStatement.setString(2, getComplaint_content());
			prepareStatement.setString(3, getComplaint_time());
			prepareStatement.setInt(4, getDept_type_id());
			prepareStatement.setInt(5, getSub_dept_type_id());
			prepareStatement.setString(6, getAssigned_police_report());
			prepareStatement.setInt(7, getAssigned_police_id());
			prepareStatement.setString(8, getComplain_subject());
			prepareStatement.setInt(9, getVps_id());

			res = prepareStatement.executeUpdate();

			result = prepareStatement.getGeneratedKeys();
			if (result.next()) {
				setComplaint_id(result.getInt(1));
			}
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

	public String delete() {
		PreparedStatement prepareStatement = null;
		ResultSet result = null;
		int res = 0;
		try {
			prepareStatement = DBConnection.connect().prepareStatement(
					DELETE_COMPLAINT, Statement.RETURN_GENERATED_KEYS);
			prepareStatement.setInt(1, getComplaint_id());

			res = prepareStatement.executeUpdate();

			result = prepareStatement.getGeneratedKeys();
			if (result.next()) {
				setComplaint_id(result.getInt(1));
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

	// retrieve complain specific to user

	public JSONArray citizenComplain(int uid) {
		PreparedStatement prepareStatement = null;
		ResultSet result = null;
		JSONObject json;
		JSONArray jArray = new JSONArray();
		ArrayList<String> complaint_id = new ArrayList<String>();
		ArrayList<String> complaint_content = new ArrayList<String>();
		ArrayList<String> complaint_time = new ArrayList<String>();
		ArrayList<String> complaint_status = new ArrayList<String>();
		ArrayList<String> complaint_subject = new ArrayList<String>();
		try {

			prepareStatement = DBConnection.connect().prepareStatement(
					SELECT_CITIZEN_COMPLAIN);
			prepareStatement.setInt(1, uid);

			result = prepareStatement.executeQuery();
			try {
				while (result.next()) {
					json = new JSONObject();
					json.put(COMPLAIN_ID, result.getString(1));
					json.put(COMPLAINT_CONTENT, result.getString(3));
					json.put(COMPLAINT_TIME, result.getString(4));
					ComplaintStatus complainstatus = new ComplaintStatus();
					complainstatus.setComplaint_id(result.getInt(1));
					json.put(COMPLAINT_STATUS, complainstatus.select());
					json.put(COMPLAIN_SUBJECT, result.getString(9));

					jArray.put(json);

				}

			} catch (JSONException e) {

			}

			result.close();
			prepareStatement.close();
			DBConnection.connect().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block

		}

		return jArray;
	}

	public JSONArray departmentComplain(int dept_id, int vps_id) {
		PreparedStatement prepareStatement = null;
		ResultSet result = null;
		JSONObject json;
		JSONArray jArray = new JSONArray();
		ArrayList<Integer> complaint_id = new ArrayList<Integer>();
		ArrayList<Integer> citizen_id = new ArrayList<Integer>();
		ArrayList<String> complaint_content = new ArrayList<String>();
		ArrayList<String> complaint_time = new ArrayList<String>();
		ArrayList<String> complaint_status = new ArrayList<String>();
		ArrayList<String> complaint_subject = new ArrayList<String>();
		try {

			prepareStatement = DBConnection.connect().prepareStatement(
					SELECT_DEPARTMENT_COMPLAIN);
			prepareStatement.setInt(1, dept_id);
			prepareStatement.setInt(2, vps_id);

			result = prepareStatement.executeQuery();
			try {
				while (result.next()) {
					json = new JSONObject();
					json.put(COMPLAIN_ID, result.getString(1));
					json.put(COMPLAINT_CONTENT, result.getString(3));
					json.put(COMPLAINT_TIME, result.getString(4));
					ComplaintStatus complainstatus = new ComplaintStatus();
					complainstatus.setComplaint_id(result.getInt(1));
					json.put(COMPLAINT_STATUS, complainstatus.select());
					json.put(COMPLAIN_SUBJECT, result.getString(9));
					json.put(CITIZEN_ID, result.getString(2));

					jArray.put(json);

				}

			} catch (JSONException e) {

			}

			result.close();
			prepareStatement.close();
			DBConnection.connect().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block

		}

		return jArray;
	}

	public JSONArray departmentComplainFIR(int dept_id, int vps_id) {
		PreparedStatement prepareStatement = null;
		ResultSet result = null;
		JSONObject json;
		JSONArray jArray = new JSONArray();
		ArrayList<Integer> complaint_id = new ArrayList<Integer>();
		ArrayList<Integer> citizen_id = new ArrayList<Integer>();
		ArrayList<String> complaint_content = new ArrayList<String>();
		ArrayList<String> complaint_time = new ArrayList<String>();
		ArrayList<String> complaint_status = new ArrayList<String>();
		ArrayList<String> complaint_subject = new ArrayList<String>();
		try {

			prepareStatement = DBConnection.connect().prepareStatement(
					SELECT_DEPARTMENT_COMPLAIN_FIR);
			prepareStatement.setInt(1, dept_id);
			prepareStatement.setInt(2, vps_id);

			result = prepareStatement.executeQuery();
			try {
				while (result.next()) {
					json = new JSONObject();
					json.put(COMPLAIN_ID, result.getString(1));
					json.put(COMPLAINT_CONTENT, result.getString(3));
					json.put(COMPLAINT_TIME, result.getString(4));
					ComplaintStatus complainstatus = new ComplaintStatus();
					complainstatus.setComplaint_id(result.getInt(1));
					json.put(COMPLAINT_STATUS, complainstatus.select());
					json.put(COMPLAIN_SUBJECT, result.getString(9));
					json.put(CITIZEN_ID, result.getString(2));

					jArray.put(json);

				}

			} catch (JSONException e) {

			}

			result.close();
			prepareStatement.close();
			DBConnection.connect().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block

		}

		return jArray;
	}

	public JSONArray departmentComplainChargesheet(int dept_id, int vps_id) {
		PreparedStatement prepareStatement = null;
		ResultSet result = null;
		JSONObject json;
		JSONArray jArray = new JSONArray();
		ArrayList<Integer> complaint_id = new ArrayList<Integer>();
		ArrayList<Integer> citizen_id = new ArrayList<Integer>();
		ArrayList<String> complaint_content = new ArrayList<String>();
		ArrayList<String> complaint_time = new ArrayList<String>();
		ArrayList<String> complaint_status = new ArrayList<String>();
		ArrayList<String> complaint_subject = new ArrayList<String>();
		try {

			prepareStatement = DBConnection.connect().prepareStatement(
					SELECT_DEPARTMENT_COMPLAIN_CHARGESHEET);
			prepareStatement.setInt(1, dept_id);
			prepareStatement.setInt(2, vps_id);

			result = prepareStatement.executeQuery();
			try {
				while (result.next()) {
					json = new JSONObject();
					json.put(COMPLAIN_ID, result.getString(1));
					json.put(COMPLAINT_CONTENT, result.getString(3));
					json.put(COMPLAINT_TIME, result.getString(4));
					ComplaintStatus complainstatus = new ComplaintStatus();
					complainstatus.setComplaint_id(result.getInt(1));
					json.put(COMPLAINT_STATUS, complainstatus.select());
					json.put(COMPLAIN_SUBJECT, result.getString(9));
					json.put(CITIZEN_ID, result.getString(2));

					jArray.put(json);

				}

			} catch (JSONException e) {

			}

			result.close();
			prepareStatement.close();
			DBConnection.connect().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block

		}

		return jArray;
	}

	public JSONArray allComplain(int vps_id) {
		PreparedStatement prepareStatement = null;
		ResultSet result = null;
		JSONObject json;
		JSONArray jArray = new JSONArray();
		ArrayList<Integer> complaint_id = new ArrayList<Integer>();
		ArrayList<Integer> citizen_id = new ArrayList<Integer>();
		ArrayList<String> complaint_content = new ArrayList<String>();
		ArrayList<String> complaint_time = new ArrayList<String>();
		ArrayList<String> complaint_status = new ArrayList<String>();
		ArrayList<String> complaint_subject = new ArrayList<String>();
		try {

			prepareStatement = DBConnection.connect().prepareStatement(
					SELECT_COMPLAINT_ALL);
			prepareStatement.setInt(1, vps_id);

			result = prepareStatement.executeQuery();
			try {
				while (result.next()) {
					json = new JSONObject();
					json.put(COMPLAIN_ID, result.getString(1));
					json.put(COMPLAINT_CONTENT, result.getString(3));
					json.put(COMPLAINT_TIME, result.getString(4));
					ComplaintStatus complainstatus = new ComplaintStatus();
					complainstatus.setComplaint_id(result.getInt(1));
					json.put(COMPLAINT_STATUS, complainstatus.select());
					json.put(COMPLAIN_SUBJECT, result.getString(9));
					json.put(CITIZEN_ID, result.getString(2));

					jArray.put(json);

				}

			} catch (JSONException e) {

			}

			result.close();
			prepareStatement.close();
			DBConnection.connect().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block

		}

		return jArray;
	}

	public JSONObject citizenSpecificComplain(int complain_id, int citizen_id) {
		PreparedStatement prepareStatement = null;
		ResultSet result = null;
		JSONObject json = new JSONObject();

		try {

			prepareStatement = DBConnection.connect().prepareStatement(
					SELECT_CITIZEN_SPECIFIC_COMPLAIN);
			prepareStatement.setInt(1, complain_id);

			result = prepareStatement.executeQuery();
			try {
				while (result.next()) {
					json.put(COMPLAIN_ID, result.getInt(1));
					json.put(COMPLAINT_CONTENT, result.getString(3));
					json.put(DEPT_TYPE_ID, result.getInt(5));
					json.put(COMPLAIN_SUBJECT, result.getString(9));
					json.put(SUB_DEPT_ID, result.getInt(6));
					json.put(ASSIGNED_POLICE_ID, result.getInt(8));

				}
				prepareStatement = DBConnection.connect().prepareStatement(
						SELECT_CITIZEN);
				prepareStatement.setInt(1, citizen_id);

				result = prepareStatement.executeQuery();
				if (result.next())
					json.put("gender", result.getString(10));
				Address address = new Address();
				address.setAddress_id(result.getInt(7));
				json.put(ADDRESS_ID, address.select());
				json.put("identification_copy", result.getString(4));

				json.put(CITIZEN_NAME, result.getString(2));

			} catch (JSONException e) {

			}

			result.close();
			prepareStatement.close();
			DBConnection.connect().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block

		}

		System.out.println(json.toString());
		return json;
	}

	public String retrieve() {
		PreparedStatement prepareStatement = null;
		ResultSet result = null;
		JSONObject json = new JSONObject();
		int res = 0;
		try {
			if (getComplaint_id() != 0) {
				prepareStatement = DBConnection.connect().prepareStatement(
						SELECT_COMPLAINT);
				prepareStatement.setInt(1, getComplaint_id());

			} else {
				prepareStatement = DBConnection.connect().prepareStatement(
						SELECT_COMPLAINT_ALL);

			}
			result = prepareStatement.executeQuery();
			Citizen citizen = new Citizen();
			ComplaintStatus complaintStatus = new ComplaintStatus();
			Department department = new Department();
			SubDepartment subDepartment = new SubDepartment();
			while (result.next()) {
				try {
					json.put(COMPLAINT_ID, result.getString(1));
					citizen.setUid(result.getInt(2));
					json.put(CITIZEN_ID, citizen.retrive());
					json.put(COMPLAINT_CONTENT, result.getString(3));
					json.put(COMPLAINT_TIME, result.getString(4));
					complaintStatus.setComplaint_id(result.getInt(1));
					json.put(COMPLAINT_STATUS, complaintStatus.retrive());
					department.setDepartment_type_id(result.getInt(6));
					json.put(DEPARTMENT_TYPE, department.retrive());
					subDepartment.setDept_type_id(result.getInt(6));
					subDepartment.setSub_dept_id(result.getInt(7));
					json.put(SUB_DEPT, subDepartment.retrive());
					json.put(ASSIGNED_POLICE_ID, result.getString(8));
					json.put(ASSIGNED_POLICE_REPORT, result.getString(9));
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

	public JSONArray getClosedcomplain(int vps_id) {
		PreparedStatement prepareStatement = null;
		ResultSet result = null;
		JSONObject json = null;
		JSONArray jArray = new JSONArray();
		Citizen citizen = new Citizen();
		ComplaintStatus complaintStatus = new ComplaintStatus();
		try {
			prepareStatement = DBConnection.connect().prepareStatement(
					SELECT_CLOSED_COMPLAINTS);
			prepareStatement.setInt(1, vps_id);
			result = prepareStatement.executeQuery();
			while (result.next()) {
				try {
					json = new JSONObject();
					json.put(COMPLAINT_ID, result.getInt(1));
					complaintStatus.setComplaint_id(result.getInt(1));
					json.put(COMPLAINT_STATUS, new JSONObject(complaintStatus
							.retrive().toString())
							.getString(COMPLAINT_STATUS_NAME));
					json.put(COMPLAIN_SUBJECT, result.getString(9));
					json.put(COMPLAINT_TIME, result.getString(4));
					json.put(CITIZEN_ID, result.getInt(2));

				} catch (Exception e) {
					e.printStackTrace();
				}
				jArray.put(json);
			}
			result.close();
			prepareStatement.close();
			DBConnection.connect().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block

		}

		return jArray;
	}

	public JSONArray getALLcomplain(int dept_id) {
		PreparedStatement prepareStatement = null;
		ResultSet result = null;
		JSONObject json = null;
		JSONArray jArray = new JSONArray();
		Citizen citizen = new Citizen();
		ComplaintStatus complaintStatus = new ComplaintStatus();
		try {
			prepareStatement = DBConnection.connect().prepareStatement(
					SELECT_ALL_COMPLAINT_DEPT);
			prepareStatement.setInt(1, dept_id);
			result = prepareStatement.executeQuery();
			while (result.next()) {
				try {
					json = new JSONObject();
					json.put(COMPLAINT_ID, result.getInt(1));
					complaintStatus.setComplaint_id(result.getInt(1));
					json.put(COMPLAINT_STATUS, new JSONObject(complaintStatus
							.retrive().toString())
							.getString(COMPLAINT_STATUS_NAME));
					json.put(COMPLAIN_SUBJECT, result.getString(9));
					json.put(COMPLAINT_TIME, result.getString(4));
					json.put(CITIZEN_ID, result.getInt(2));

				} catch (Exception e) {
					e.printStackTrace();
				}
				jArray.put(json);
			}
			result.close();
			prepareStatement.close();
			DBConnection.connect().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block

		}

		return jArray;
	}

	public String updateComplainByCitizen() {
		PreparedStatement prepareStatement = null;
		ResultSet result = null;
		int res = 0;
		try {
			prepareStatement = DBConnection.connect().prepareStatement(
					UPDATE_COMPLAIN_BY_CITIZEN);
			prepareStatement.setString(1, getComplaint_content());
			prepareStatement.setString(2, getComplain_subject());
			prepareStatement.setInt(3, getComplaint_id());

			res = prepareStatement.executeUpdate();

			prepareStatement.close();
			DBConnection.connect().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block

		}
		if (res != 0)
			return SUCCESS;

		return FAILED;
	}

	/***************************************************************/

	private int complaint_id;

	public int getComplaint_id() {
		return complaint_id;
	}

	public void setComplaint_id(int complaint_id) {
		this.complaint_id = complaint_id;
	}

	public int getCitizen_id() {
		return citizen_id;
	}

	public void setCitizen_id(int citizen_id) {
		this.citizen_id = citizen_id;
	}

	public String getComplaint_content() {
		return complaint_content;
	}

	public void setComplaint_content(String compalint_content) {
		this.complaint_content = compalint_content;
	}

	public String getComplaint_time() {
		return complaint_time;
	}

	public void setComplaint_time(String complaint_time) {
		this.complaint_time = complaint_time;
	}

	public int getDept_type_id() {
		return dept_type_id;
	}

	public void setDept_type_id(int dept_type_id) {
		this.dept_type_id = dept_type_id;
	}

	public int getSub_dept_type_id() {
		return sub_dept_type_id;
	}

	public void setSub_dept_type_id(int sub_dept_type_id) {
		this.sub_dept_type_id = sub_dept_type_id;
	}

	public String getAssigned_police_report() {
		return assigned_police_report;
	}

	public void setAssigned_police_report(String assigned_police_report) {
		this.assigned_police_report = assigned_police_report;
	}

	public int getAssigned_police_id() {
		return assigned_police_id;
	}

	public void setAssigned_police_id(int assigned_police_id) {
		this.assigned_police_id = assigned_police_id;
	}

	public Complain() {
		super();
	}

	private int citizen_id;
	private String complaint_content;

	private String complaint_time;

	private int dept_type_id;
	private int sub_dept_type_id;
	private String assigned_police_report;
	private int assigned_police_id;
	private String complain_subject;
	private int vps_id;

	public String getComplain_subject() {
		return complain_subject;
	}

	public void setComplain_subject(String complain_subject) {
		this.complain_subject = complain_subject;
	}

	public int getVps_id() {
		return vps_id;
	}

	public void setVps_id(int vps_id) {
		this.vps_id = vps_id;
	}
}
