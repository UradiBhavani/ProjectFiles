package com.cg.utility;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.cg.exceptions.InsuranceException;

import oracle.jdbc.driver.OracleDriver;

public class JDBCUtility {
	static Connection connection = null;
	
	public static Connection getConnection() throws InsuranceException {
		Driver driver = new OracleDriver();
		
		try {
			DriverManager.registerDriver(driver);
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","Capgemini1234");
		} catch (SQLException e) {
			throw new InsuranceException("Error while connecting to the database");
		}
		
		return connection;
	}

}
