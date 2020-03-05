package com.cg.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.cg.controller.LoginServlet;
import com.cg.queries.QueryConstants;
import com.cg.utility.JDBCUtility;

public class LoginDAOImpl implements ILoginDAO{
	
	Connection connection = null;
	PreparedStatement statement = null;

	static final Logger LOGGER = Logger.getLogger(LoginDAOImpl.class);
	
	@Override
	public int validate(String username, String password) throws  Exception {
		connection = JDBCUtility.getConnection();
		String query = QueryConstants.VALIDATE_CREDENTIALS;
		int id=0;
		
		try {
			LOGGER.info("Inside Validate method in  LoginDAO Implementation");
			
			statement = connection.prepareStatement(query);
			
			statement.setString(1, username);
			statement.setString(2, password);
			System.out.println(username);
			ResultSet res = statement.executeQuery();
			
			if(res.next()) {
				id=1;
			}
			
		} catch (SQLException e) {
			
			LOGGER.error("Erro while validating the user");
			
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
			LOGGER.info("Inside the get role code method in LoginDAO Implementation");
			
			statement = connection.prepareStatement(query);
			statement.setString(1, username);
			
			ResultSet res = statement.executeQuery();
			if(res.next())
				roleCode=res.getString(1);
			
		} catch (SQLException e) {
			
			LOGGER.error("Error while retriving the rolecode from DB in LoginDAO Implementation");
			
		}
		
		return roleCode;
	}

	@Override
	public int resetPassword(String username, String oldPassword) throws Exception {
		connection = JDBCUtility.getConnection();
		int res=0;
		try {
			
			LOGGER.info("Inside reset password method in Login DAO Implementation");
			
			
			String query = "UPDATE USERROLE SET PASSWORD=? WHERE USERNAME=?";
			statement = connection.prepareStatement(query);
			statement.setString(1, oldPassword);
			statement.setString(2, username);
			
			res = statement.executeUpdate();
		}catch(SQLException e) {
			LOGGER.error("Error while updating the password");
		}
		
		
		return res;
	}

}
