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
import com.mysql.jdbc.Connection;

public class PoliceOfficer implements Constants, QueryConst {

	public PoliceOfficer(int officer_id, String email_id, int address_id,
			int vps_id, int dept_type_id, String password, String officer_name,
			String mobile, String identification_copy, String gender) {
		super();
		this.officer_id = officer_id;
		this.email_id = email_id;
		this.address_id = address_id;
		this.vps_id = vps_id;
		this.dept_type_id = dept_type_id;
		this.password = password;
		this.officer_name = officer_name;
		this.mobile = mobile;
		this.identification_copy = identification_copy;
		this.gender = gender;
	}

	private PreparedStatement prepareStatement = null;

	private int officer_id;
	private String email_id;
	private int address_id;
	private int vps_id;
	private int dept_type_id;
	private String password;
	private String officer_name;
	private String mobile;
	private String identification_copy;
	private String gender;
	private String role;

	public PoliceOfficer() {
		super();
	}

	public JSONObject retrieveByDept() {
		JSONObject json = null;
		JSONArray jArray = new JSONArray();
		JSONObject resp = new JSONObject();
		PreparedStatement prepareStatement = null;
		try {
			prepareStatement = DBConnection.connect().prepareStatement(
					SELECT_POLICEOFFICER_BYDEPT);
			ResultSet result = prepareStatement.executeQuery();
			System.out.println("hello");
			while (result.next()) {
				try {
					json = new JSONObject();
					json.put(OFFICER_ID, result.getString(OFFICER_ID));
					json.put(OFFICER_EMAIL_ID,
							result.getString(OFFICER_EMAIL_ID));
					json.put(OFFICER_NAME, result.getString(OFFICER_NAME));

				} catch (JSONException e) {
				}
				jArray.put(json);

			}
			resp.put("content", jArray);
			result.close();
			prepareStatement.close();
			DBConnection.connect().close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resp;

	}

	public String create() {
		int res = 0;

		try {
			 
			prepareStatement = DBConnection.connect().prepareStatement(INSERT_OFFICER,
					Statement.RETURN_GENERATED_KEYS);
			prepareStatement.setString(1, getEmail_id()); // automatic
			prepareStatement.setString(2, getPassword());
			prepareStatement.setString(3, getOfficer_name());
			prepareStatement.setInt(4, getAddress_id());
			prepareStatement.setString(5, getIdentification_copy());
			prepareStatement.setString(6, getMobile());
			prepareStatement.setInt(7, getVps_id()); // automatic
			prepareStatement.setInt(8, getDept_type_id()); // intially empty
			prepareStatement.setString(9, getGender());

			res = prepareStatement.executeUpdate();
			System.out.println(res);
			ResultSet result = prepareStatement.getGeneratedKeys();
			result.next();
			setOfficer_id(result.getInt(1));
			result.close();
			prepareStatement.close();
			DBConnection.connect().close();

		} catch (SQLException e) {
			e.printStackTrace();

		}
		try {
			
			prepareStatement = DBConnection.connect().prepareStatement(INSERT_ROLE);
			prepareStatement.setInt(1, getOfficer_id()); // automatic
			prepareStatement.setString(2, getRole());
			res = prepareStatement.executeUpdate();

			prepareStatement.close();
			DBConnection.connect().close();
		} catch (Exception e) {
			e.printStackTrace();
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
					SELECT_POLICEOFFICER);
			prepareStatement.setInt(1, getOfficer_id());

			ResultSet result = prepareStatement.executeQuery();

			while (result.next()) {
				try {

					json.put(OFFICER_ID, result.getString(1));
					json.put(OFFICER_EMAIL_ID, result.getString(2));
					json.put(OFFICER_PASSWORD, result.getString(3));
					json.put(OFFICER_NAME, result.getString(4));
					json.put(OFFICER_ADDRESS_ID, result.getInt(5));
					json.put(OFFICER_IDENTIFICATION_COPY, result.getString(6));
					json.put(OFFICER_MOBILE_NUMBER, result.getString(7));
					json.put(OFFICER_VPS_ID, result.getInt(8));
					json.put(OFFICER_DEPT_ID, result.getInt(9));
					json.put(GENDER, result.getString(10));
					// json.put(DATE_OF_BIRTH, result.getString(11));

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

	public JSONObject checkLogin() {
		int VAL = 0;
		int Dept_id = 0;
		int vps_id = 0;
		String dept = null;
		ResultSet result = null;
		JSONObject json = null;
		PreparedStatement prepareStatement = null;

		try {
			prepareStatement = DBConnection.connect().prepareStatement(
					CHECK_POLICE);
			prepareStatement.setString(1, getEmail_id());
			prepareStatement.setString(2, getPassword());

			result = prepareStatement.executeQuery();

			if (result.next()) {
				Dept_id = result.getInt(9);
				VAL = result.getInt(1);
				vps_id = result.getInt(8);
			}

			result.close();
			prepareStatement = DBConnection.connect().prepareStatement(
					CHECK_ROLE);
			prepareStatement.setInt(1, VAL);
			prepareStatement.setString(2, getRole());
			result = prepareStatement.executeQuery();

			if (result.next()) {
				prepareStatement = DBConnection.connect().prepareStatement(
						SELECT_DEPARTMENT);
				prepareStatement.setInt(1, Dept_id);

				result = prepareStatement.executeQuery();
				if (result.next()) {
					dept = result.getString(2);
				}

				try {
					json = new JSONObject();
					json.put("pid", VAL);
					json.put("dept", dept);
					json.put("dept_id", Dept_id);
					json.put("vps_id", vps_id);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				result.close();

				return json;

			}

			prepareStatement.close();
			DBConnection.connect().close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public int checktrafficLogin() {
		int VAL = 0;
		int Dept_id = 0;
		int vps_id = 0;
		String dept = null;
		ResultSet result = null;
		JSONObject json = null;
		PreparedStatement prepareStatement = null;

		try {
			prepareStatement = DBConnection.connect().prepareStatement(
					CHECK_POLICE);
			prepareStatement.setString(1, getEmail_id());
			prepareStatement.setString(2, getPassword());

			result = prepareStatement.executeQuery();

			if (result.next()) {
				Dept_id = result.getInt(9);
				VAL = result.getInt(1);
				vps_id=result.getInt(8);
			}

			result.close();
			prepareStatement = DBConnection.connect().prepareStatement(
					CHECK_ROLE);
			prepareStatement.setInt(1, VAL);
			prepareStatement.setString(2, getRole());
			result = prepareStatement.executeQuery();

			if (result.next()) {

 System.out.println("done............");
				return VAL;

			}

			prepareStatement.close();
			DBConnection.connect().close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;
	}


	public String update() {
		int res = 0;
		try {
			prepareStatement = DBConnection.connect().prepareStatement(
					UPDATE_OFFICER);
			prepareStatement.setString(1, getEmail_id());
			prepareStatement.setString(2, getOfficer_name());
			prepareStatement.setString(3, getMobile());
			prepareStatement.setString(4, getGender());
			prepareStatement.setInt(5, getOfficer_id()); // automatic
			res = prepareStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		}

		if (res != 0)
			return SUCCESS;

		return FAILED;
	}

	public String updatePass(int pid, String pass) throws SQLException {
		int res = 0;
		try {
			prepareStatement = DBConnection.connect().prepareStatement(
					UPDATE_OFFICER_PASS);
			prepareStatement.setString(1, pass);
			prepareStatement.setInt(2, pid); // automatic
			res = prepareStatement.executeUpdate();
			System.out.println(pid);
		} catch (SQLException e) {
			e.printStackTrace();

		}

		if (res != 0)
			return SUCCESS;

		return FAILED;
	}

	public JSONArray retriveByVPSId() {
		JSONObject json = new JSONObject();
		JSONArray jArray = new JSONArray();
		PreparedStatement prepareStatement = null;
		try {
			prepareStatement = DBConnection.connect().prepareStatement(
					SELECT_POLICEOFFICER);
			prepareStatement.setInt(1, getVps_id());

			ResultSet result = prepareStatement.executeQuery();

			while (result.next()) {
				try {

					json.put(OFFICER_ID, result.getString(1));
					json.put(OFFICER_EMAIL_ID, result.getString(2));
					json.put(OFFICER_PASSWORD, result.getString(3));
					json.put(OFFICER_NAME, result.getString(4));
					json.put(OFFICER_ADDRESS_ID, result.getInt(5));
					json.put(OFFICER_IDENTIFICATION_COPY, result.getString(6));
					json.put(OFFICER_MOBILE_NUMBER, result.getString(7));
					json.put(OFFICER_VPS_ID, result.getInt(8));
					json.put(OFFICER_DEPT_ID, result.getInt(9));
					json.put(GENDER, result.getString(10));
					// json.put(DATE_OF_BIRTH, result.getString(11));

				} catch (JSONException e) {
				}
				jArray.put(json);
			}
			result.close();
			prepareStatement.close();
			DBConnection.connect().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return jArray;
	}

	public int getOfficer_id() {
		return officer_id;
	}

	public void setOfficer_id(int officer_id) {
		this.officer_id = officer_id;
	}

	public String getEmail_id() {
		return email_id;
	}

	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}

	public int getVps_id() {
		return vps_id;
	}

	public void setVps_id(int vps_id) {
		this.vps_id = vps_id;
	}

	public int getDept_type_id() {
		return dept_type_id;
	}

	public void setDept_type_id(int dept_type_id) {
		this.dept_type_id = dept_type_id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getOfficer_name() {
		return officer_name;
	}

	public void setOfficer_name(String officer_name) {
		this.officer_name = officer_name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getIdentification_copy() {
		return identification_copy;
	}

	public void setIdentification_copy(String identification_copy) {
		this.identification_copy = identification_copy;
	}

	public int getAddress_id() {
		return address_id;
	}

	public void setAddress_id(int address_id) {
		this.address_id = address_id;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
