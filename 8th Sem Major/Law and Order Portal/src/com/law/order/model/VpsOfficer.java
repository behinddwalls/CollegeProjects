package com.law.order.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.law.order.controller.utility.Constants;
import com.law.order.model.utility.DBConnection;
import com.law.order.model.utility.QueryConst;

public class VpsOfficer implements Constants, QueryConst {

	private PreparedStatement prepareStatement = null;
	private int vps_id;
	private int vps_alloted_officer_id;

	public VpsOfficer(int vps_id, int vps_alloted_officer_id) {
		super();
		this.vps_id = vps_id;
		this.vps_alloted_officer_id = vps_alloted_officer_id;
	}

	public VpsOfficer() {
		super();
	}

	public String create() {
		int res = 0;
		try {
			prepareStatement = DBConnection.connect().prepareStatement(
					INSERT_VPS_OFFICER);
			prepareStatement.setInt(1, getVps_id()); // automatic
			prepareStatement.setInt(2, getVps_alloted_officer_id());

			res = prepareStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		}
		if (res != 0)
			return SUCCESS;

		return FAILED;
	}

	public String delete(int id) {
		int res = 0;
		try {
			prepareStatement = DBConnection.connect().prepareStatement(
					DELETE_VPS_OFFICER);
			prepareStatement.setInt(1, id); // automatic

			res = prepareStatement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block

		}
		if (res != 0)
			return SUCCESS;

		return FAILED;
	}

	public List<Integer> selectVpsId() {
		ResultSet res = null;
		ArrayList<Integer> list = new ArrayList<Integer>();
		try {
			prepareStatement = DBConnection.connect().prepareStatement(
					SELECT_VPS_OFFICER_ID);
			prepareStatement.setInt(1, getVps_id()); // automatic

			res = prepareStatement.executeQuery();
			while (res.next()) {
				list.add(res.getInt(2));
			}

			res.close();
			prepareStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();

		}

		return list;
	}

	public int getVps_id() {
		return vps_id;
	}

	public void setVps_id(int vps_id) {
		this.vps_id = vps_id;
	}

	public int getVps_alloted_officer_id() {
		return vps_alloted_officer_id;
	}

	public void setVps_alloted_officer_id(int vps_alloted_officer_id) {
		this.vps_alloted_officer_id = vps_alloted_officer_id;
	}

}
