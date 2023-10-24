package com.miniproject;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

	Connection con = null;

	public Connection getConnectionDetails() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mypractise", "root", "nitin@2697");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}

}
