package com.app.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtilJDBC {

	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/springmvc";
	private static final String DB_USERNAME = "root";
	private static final String DB_PWD = "admin";
	private static Connection conn = null;
	
	private DBUtilJDBC(){
		
	}
	public static Connection getDBConnection() throws ClassNotFoundException, SQLException {
		try{
		Class.forName(DB_DRIVER);
		conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PWD);
		if(conn == null){
			return conn;
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return conn;
	}
}
