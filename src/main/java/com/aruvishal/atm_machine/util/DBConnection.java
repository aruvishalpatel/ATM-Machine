package com.aruvishal.atm_machine.util;

import java.sql.Connection;

import java.sql.DriverManager;

public class DBConnection {
	public static Connection getConnection() throws Exception {
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/atmmachine?user=root&password=");
		} catch (ClassNotFoundException e) {
			// Logger.getLoggger(DBconnection.class)1
		}
		return connection;
	}

}
