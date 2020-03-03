package com.cg.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.cg.dto.Policy;
import com.cg.exceptions.InsuranceException;
import com.cg.utility.JDBCUtility;

public class AddPolicyDAOImpl implements IAddPolicyDAO{

	
	Connection connection = null;
	PreparedStatement statement = null;
	
	@Override
	public int addPolicy(Policy policy) throws InsuranceException {
		connection = JDBCUtility.getConnection();
		
		int res=0;
		try {
			String query = "insert into policy values(?,?,?,?)";
			statement = connection.prepareStatement(query);
			
			statement.setInt(1,policy.getPolicyNumber());
			statement.setInt(2,policy.getPolicyPremium());
			statement.setInt(3, policy.getAccountNumber());
			statement.setString(4, policy.getPolicyType());
			
			res = statement.executeUpdate();
			
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//throw new InsuranceException("SQLException: error while inserting data into the database");
			System.out.println("SQLException: error while inserting data into the database  --  "+e.getMessage());
		}
		
		
		return res;
	}

}
