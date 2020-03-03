package com.cg.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cg.utility.JDBCUtility;

public class UserDAOImpl implements IUserDAO{
	
	Connection connection = null;
	PreparedStatement statement = null;

	@Override
	public int validate(String username, String password) throws  Exception {
		connection = JDBCUtility.getConnection();
		System.out.println();
		String query = "select * from userrole where username=? and password=?";
		int id=0;
		try {
			statement = connection.prepareStatement(query);
			
			statement.setString(1, username);
			statement.setString(2, password);
			System.out.println(username);
			ResultSet res = statement.executeQuery();
			
			if(res.next()) {
				id=1;
			}
			
		} catch (SQLException e) {
			System.out.println("Problem occured during creating PS statement"+e); 
		}
		System.out.println("id : "+id);
		
		return id;
	}

	@Override
	public String getRoleCode(String username) throws SQLException, Exception {
connection = JDBCUtility.getConnection();
		
		String query = "select rolecode from userrole where username=?";
		String roleCode="";
		try {
			statement = connection.prepareStatement(query);
			
			statement.setString(1, username);
			
			ResultSet res = statement.executeQuery();
			if(res.next())
				roleCode=res.getString(1);
			
		} catch (SQLException e) {
			System.out.println("Problem occured during creating PS statement"+e); 
		}
		
		return roleCode;
	}

}
