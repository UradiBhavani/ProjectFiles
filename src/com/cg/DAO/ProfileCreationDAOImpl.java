package com.cg.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.cg.dto.UserRole;
import com.cg.utility.JDBCUtility;

public class ProfileCreationDAOImpl implements IProfileCreationDAO{

	Connection connection = null;
	PreparedStatement statement = null;
	@Override
	public int createProfile(UserRole userRole) throws Exception {
		connection = JDBCUtility.getConnection();
		int res=0;
		try {
			String query="insert into userrole values(?,?,?,?,?)";
			statement = connection.prepareStatement(query);
			statement.setString(1, userRole.getUserName());
			statement.setString(2, userRole.getPassword());
			statement.setString(3, userRole.getRoleCode());
			statement.setString(4, userRole.getAgentId());
			statement.setInt(5, userRole.getAccountNumber());
			
			
			
			
			res = statement.executeUpdate();
			System.out.println(res + "inserted ");
		} catch (Exception e) {
			System.out.println("Error while creating claim for the user"+e.getMessage());
		}
		
		
		return res;
	}
}
