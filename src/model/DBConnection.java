package model;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Servlet implementation class DBConnection
 */

public class DBConnection {
	private static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
	private static final String CONNECT_URL = "jdbc:mysql://localhost/demo101";
	//private static final String CONNECT_URL = "jdbc:mysql://localhost:3308/demo101";
	private static final String USER_NAME = "root";
	private static final String PASSWORD = "root";
	private Connection con;

	public Connection getConnect() throws Exception {
		if (con == null) {
			Class.forName(DRIVER_NAME);
			con = DriverManager.getConnection(CONNECT_URL, USER_NAME, PASSWORD);
		}
		return con;
	}

	public void closeConnect() throws Exception {
		if (con != null) {
			con.close();
			con = null;
		}
	}
}