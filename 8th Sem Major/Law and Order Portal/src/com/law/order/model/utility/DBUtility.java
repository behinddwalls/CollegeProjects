package com.law.order.model.utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.law.order.controller.VpsController;
import com.law.order.controller.utility.Constants;
import com.law.order.model.Address;

public class DBUtility implements Constants {

	public ArrayList<String> getPincodeTable() throws SQLException {
		ArrayList<String> list = new ArrayList<String>();
		Connection conn = DBConnection.connect();
		PreparedStatement preparedStatement = conn
				.prepareStatement("select * from pincode_table");
		ResultSet rs = (ResultSet) preparedStatement.executeQuery();
		while (rs.next()) {
			list.add(rs.getString(1));
		}

		rs.close();
		preparedStatement.close();
		conn.close();
		return list;
	}

	public String insertAreaId(int vpsId, String pincode) throws SQLException {
		Connection conn = DBConnection.connect();
		PreparedStatement preparedStatement = conn
				.prepareStatement("select * from area_pincode_table where pincode=?");
		preparedStatement.setString(1, pincode);
		ResultSet rs = preparedStatement.executeQuery();
		int areaId = -1;
		if (rs.next()) {
			System.out.println("hello");
			areaId = rs.getInt(1);
			System.out.println("hello" + areaId);
		}
		preparedStatement.close();
		preparedStatement = conn
				.prepareStatement("insert into vps_area_list values(?,?)");
		preparedStatement.setInt(1, vpsId);
		preparedStatement.setInt(2, areaId);
		int res = preparedStatement.executeUpdate();

		rs.close();
		preparedStatement.close();
		conn.close();
		if (res != 0)
			return SUCCESS;

		return FAILED;
	}

	public List<String> getPincodeAreaWise(List<Integer> areaidList)
			throws SQLException {
		Connection conn = DBConnection.connect();
		ArrayList<String> list = new ArrayList<String>();
		for (int areaId : areaidList) {
			PreparedStatement preparedStatement = conn
					.prepareStatement("select * from area_pincode_table where area_id=?");
			preparedStatement.setInt(1, areaId);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				list.add(rs.getString(2));
			}

			rs.close();
			preparedStatement.close();
		}
		return list;
	}

	public List<Integer> getDeptForVPS(int vpsId) throws JSONException {
		ArrayList<Integer> list = new ArrayList<Integer>();
		VpsController vps = new VpsController();
		Response res = vps.getDeptIdList(vpsId);
		JSONArray jArray = new JSONArray(res.getEntity().toString());
		for (int i = 0; i < jArray.length(); i++) {
			list.add(new JSONObject(jArray.get(i).toString())
					.getInt("vps_department_id"));

		}
		return list;
	}

	public String insertCrimeLT(int complainid, int sub_dept_id,
			String crimeTime, String pincode, String address)
			throws SQLException {
		Address addr = new Address(0, pincode, address);
		addr.create();
		int addr_id = addr.getAddress_id();

		Connection conn = DBConnection.connect();

		PreparedStatement preparedStatement = conn
				.prepareStatement("insert into departments_vps_table values(?,?,?,?)");
		preparedStatement.setInt(1, complainid);
		preparedStatement.setInt(2, sub_dept_id);
		preparedStatement.setInt(3, addr_id);
		preparedStatement.setString(4, crimeTime);

		int res = preparedStatement.executeUpdate();
		preparedStatement.close();
		conn.close();
		if (res != 0)
			return SUCCESS;

		return FAILED;
	}

	public JSONObject retrieveCrimeLT(int complainid) throws SQLException,
			JSONException {
		Connection conn = DBConnection.connect();

		PreparedStatement preparedStatement = conn
				.prepareStatement("select * from departments_vps_table where complaint_id=?");
		preparedStatement.setInt(1, complainid);

		ResultSet res = preparedStatement.executeQuery();
		JSONObject jObject = new JSONObject();
		if (res.next()) {
			jObject.put("crime_time", res.getString(4));
			int addr_id = res.getInt(3);
			PreparedStatement preparedStatement1 = conn
					.prepareStatement("select * from address_table where address_id=?");
			preparedStatement1.setInt(1, addr_id);
			ResultSet rs = preparedStatement1.executeQuery();
			if(rs.next())
			{
				jObject.put("address_block", rs.getString(2));
				jObject.put("pincode", rs.getString(3));
			}
			preparedStatement1.close();

		}
		preparedStatement.close();
		conn.close();
		
		return jObject;

	}
	public String updateImagePath(String ref, String path, int id)
			throws SQLException {
		Connection conn = DBConnection.connect();
		String query = "";
		if (ref.equals("reg")) {
			query = "update citizen_detail set identification_copy=? where citizen_id=?";
		} else if (ref.equals("citizen_profile")) {
			query = "update citizen_detail set profilepic=? where citizen_id=?";
		} else if (ref.equals("police_profile")) {
			query = "update police_officer_detail set identification_copy=? where officer_id=?";
		}

		PreparedStatement preparedStatement = conn.prepareStatement(query);
		preparedStatement.setString(1, path);
		preparedStatement.setInt(2, id);

		int res = preparedStatement.executeUpdate();
		preparedStatement.close();
		conn.close();
		if (res != 0)
			return SUCCESS;

		return FAILED;
	}

}
