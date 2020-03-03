package com.cg.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.cg.dto.Claim;
import com.cg.dto.Policy;
import com.cg.utility.JDBCUtility;

public class AgentClaimCreationDAOImpl implements IAgentClaimCreationDAO{

	Connection connection = null;
	PreparedStatement statement = null;
	
	@Override
	public List<Policy> getclaimDetails(String agentUserName) throws Exception {
		List<Policy> listOfPolicies = new ArrayList<Policy>();
		connection = JDBCUtility.getConnection();
	
		try {
			String queryAccountNum = "select accountnumber from userrole where agentId=?";
			statement=connection.prepareStatement(queryAccountNum);
			statement.setString(1, agentUserName);
			
			ResultSet res = statement.executeQuery();
			List<Integer> accountNumber = new ArrayList<>();
			while(res.next()) {
				accountNumber.add(res.getInt("accountNumber"));
			}
			int i = 0;
			System.out.println("Account number : "+accountNumber);
			while (i < accountNumber.size()) {
				String query = "select * from policy where accountNumber=?";
				statement = connection.prepareStatement(query);
				statement.setInt(1, accountNumber.get(i));
				res = statement.executeQuery();
				while (res.next()) {
					Policy policy = new Policy();
					policy.setPolicyNumber(res.getInt("policyNumber"));
					policy.setPolicyPremium(res.getInt("policyPremium"));
					policy.setAccountNumber(res.getInt("accountNumber"));
					policy.setPolicyType(res.getString("policyType"));
					listOfPolicies.add(policy);
				} 
				i++;
			}
			System.out.println("Agent claim Size = "+listOfPolicies.size());
		}catch(Exception e) {
			System.out.println(e.getMessage()+"error in agent claim select query");
		}finally {
			connection.close();
		}
		return listOfPolicies;

	}
	
	
	@Override
	public List<Policy> getclaimDetails(String agentUserName,String clientUserName) throws Exception {
		List<Policy> listOfPolicies = new ArrayList<Policy>();
		connection = JDBCUtility.getConnection();
	
		try {
			String queryAccountNum = "select accountnumber from userrole where username=? and agentId=?";
			statement=connection.prepareStatement(queryAccountNum);
			statement.setString(1, clientUserName);
			statement.setString(2, agentUserName);
			
			
			
			ResultSet res = statement.executeQuery();
			int accNum = 0;
			while(res.next()) {
				
				accNum=res.getInt("accountNumber");
			}
			
			
			
			String query = "select * from policy where accountNumber=?";
			statement=connection.prepareStatement(query);
			statement.setInt(1, accNum);
			
			res = statement.executeQuery();
			while(res.next()) {
				Policy policy = new Policy();
				policy.setPolicyNumber(res.getInt("policyNumber"));
				policy.setPolicyPremium(res.getInt("policyPremium"));
				policy.setAccountNumber(res.getInt("accountNumber"));
				policy.setPolicyType(res.getString("policyType"));
				listOfPolicies.add(policy);
			}
	
			
		}catch(Exception e) {
			System.out.println(e.getMessage()+"error in agent claim select query");
		}finally {
			connection.close();
		}
		return listOfPolicies;
	}

	@Override
	public int registerClaim(Claim claim) throws Exception {
		connection = JDBCUtility.getConnection();
		int res=0;
		try {
			String query="insert into claim values(?,?,?,?,?,?,?,?,?,?)";
			statement = connection.prepareStatement(query);
			statement.setInt(1, claim.getClaimNumber());
			statement.setString(2, claim.getClaimReason());
			statement.setString(3, claim.getAccidentLocationStreet());
			statement.setString(4, claim.getAccidentCity());
			statement.setString(5, claim.getAccidentState());
			statement.setInt(6, claim.getAccidentZip());
			statement.setString(7, claim.getClaimType());
			statement.setInt(8, claim.getPolicyNumber());
			statement.setString(9, claim.getUserName());
			statement.setString(10, claim.getStatus());
			
			
			System.out.println("Claim : "+claim);
			
			res = statement.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error while creating claim for the user by agent"+e.getMessage());
		}
		
		
		System.out.println(res +"row claim details");
		
		return res;
	}


}
