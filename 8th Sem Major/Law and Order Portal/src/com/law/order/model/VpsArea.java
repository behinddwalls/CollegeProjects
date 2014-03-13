package com.law.order.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.law.order.model.utility.DBConnection;
import com.law.order.model.utility.QueryConst;

public class VpsArea implements QueryConst {

	public List<String> getAreaNameForVPS(int vpsId) throws SQLException {
		Connection conn = DBConnection.connect();
		PreparedStatement preparedStatement = conn
				.prepareStatement(SELECT_VPS_AREA_LIST);
		preparedStatement.setInt(1, vpsId);
		ResultSet rs = preparedStatement.executeQuery();
		ArrayList<String> list = new ArrayList<String>();
		while (rs.next()) {
			list.add(rs.getString(4));
		}
		rs.close();
		preparedStatement.close();
		return list;

	}

	public List<Integer> getAreaIdForVPS(int vpsId) throws SQLException
	{
		Connection conn = DBConnection.connect();
		PreparedStatement preparedStatement = conn
				.prepareStatement(SELECT_VPS_AREA_LIST);
		preparedStatement.setInt(1, vpsId);
		ResultSet rs = preparedStatement.executeQuery();
		ArrayList<Integer> list = new ArrayList<Integer>();
		while (rs.next()) {
			list.add(rs.getInt(3));
		}
		rs.close();
		preparedStatement.close();
		return list;
		
		
		
	}
	
	public String delete(int vpsid) {
		int res = 0;
		try {
			PreparedStatement prepareStatement = DBConnection.connect().prepareStatement(
					DELETE_VPS_AREA_ID);
			prepareStatement.setInt(1, vpsid); // automatic

			res = prepareStatement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block

		}
		if (res != 0)
			return SUCCESS;

		return FAILED;
	}
	
	
	

}
