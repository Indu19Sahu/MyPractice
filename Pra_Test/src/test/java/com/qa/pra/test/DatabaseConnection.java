package com.qa.pra.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This set of code is in working state
 * 
 * */

public class DatabaseConnection {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		
		String localhost = "localhost";
		String port = "3306";
		
		Connection con =  DriverManager.getConnection("jdbc:mysql://"+localhost+":"+port+"/my_db", "root", "Root");
		
		Statement st = con.createStatement();
		ResultSet rs =  st.executeQuery("Select * from companies");
		while(rs.next()){
			System.out.println(rs.getString("email"));
			System.out.println(rs.getString("password"));
		}
	}

}
