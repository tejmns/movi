package org.java.movi.dao;

import java.sql.Connection;
import java.sql.DriverManager;



public class Database {
	
	
	static public  Connection connectToDatabase() {
		Connection connection = null;
		
		try {

			Class.forName("com.mysql.jdbc.Driver");


			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javapapers","root","root");
		}
		catch(Exception e)
		{
			System.out.println("SQL exception");
		}

		return connection;
	}
}