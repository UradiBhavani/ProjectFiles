package com.cg.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import com.cg.dto.PolicyDetails;
import com.cg.utility.JDBCUtility;

public class PolicyDetailsDaoImpl implements IPolicyDetailsDAO{

	Connection connection = null;
	PreparedStatement statement = null;
	
	@Override
	public int addPolicyDetails(PolicyDetails policyDetails) throws Exception {
		
		connection = JDBCUtility.getConnection();
		int res=0;
		System.out.println("In policy details DAO"+policyDetails);
		try {
			String query="insert into policydetails values(?,?,?,?)";
			statement = connection.prepareStatement(query);
			statement.setInt(1, policyDetails.getPolicyNumber());
			statement.setString(2, policyDetails.getQuestionId());
			statement.setString(3, policyDetails.getAnswer());
			statement.setInt(4, policyDetails.getClaimNumber());
			
			
			
			
			res = statement.executeUpdate();
			System.out.println(res + "inserted ");
		} catch (Exception e) {
			System.out.println("Error in policy details dao "+e.getMessage());
		}
		
		
		return res;
	}

}
