package com.law.order.model.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.law.order.controller.utility.Constants;


public class DBConnection implements Constants {

	public static java.sql.Connection connect() {

		try {
			Class.forName(DB_DRIVER);
		} catch (ClassNotFoundException e) {
			return null;

		}

		Connection connection = null;

		try {
			connection = DriverManager.getConnection(DB_HOST
					+ DB_PORT + DB_NAME, DB_USER, DB_PASSWORD);

		} catch (SQLException e) {
			return null;

		}

		if (connection != null) {
			return connection;
		} else {
			return null;
		}
	}

}
