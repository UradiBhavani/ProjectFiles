package com.cg.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.cg.dto.Policy;
import com.cg.dto.ShowClaimDetails;
import com.cg.utility.JDBCUtility;

public class AgentSearchBasedOnFilterDAOImpl implements IAgentSearchBasedOnFilterDAO{

	Connection connection = null;
	PreparedStatement statement = null;
	
	
	@Override
	public List<ShowClaimDetails> getClaimDetails(String username, String filter,int accountNumber) throws Exception {
		List<ShowClaimDetails> showClaimDetailsList = new ArrayList<ShowClaimDetails>();
		connection = JDBCUtility.getConnection();
		
		try {
			String query="";
			ResultSet res=null;
			
			if(filter.equals("all")) {
				query = "select p.accountNumber,p.policyNumber,c.claimNumber,p.policyPremium,p.policyType,c.status from policy p, claim c where p.accountNumber=? and p.policyNumber=c.policyNumber";
				statement = connection.prepareStatement(query);
				statement.setInt(1, accountNumber);
					
			}else {
				query = "select p.accountNumber,p.policyNumber,c.claimNumber,p.policyPremium,p.policyType,c.status from policy p, claim c where p.accountNumber=? and c.status=? and p.policyNumber=c.policyNumber";
				
				statement = connection.prepareStatement(query);
				statement.setInt(1, accountNumber);
				statement.setString(2, filter);
			}res = statement.executeQuery();
			while (res.next()) {
				ShowClaimDetails claimDetails = new ShowClaimDetails();
				claimDetails.setAccountNumber(res.getInt("accountNumber"));
				claimDetails.setPolicyNumber(res.getInt("policyNumber"));
				claimDetails.setPolicyPremium(res.getInt("policyPremium"));
				claimDetails.setPolicyType(res.getString("policyType"));
				claimDetails.setClaimNumber(res.getInt("claimNumber"));
				claimDetails.setStatus(res.getString("status"));
				showClaimDetailsList.add(claimDetails);
			} 
			System.out.println("Size = "+showClaimDetailsList.size());
		} catch (Exception e) {
			System.out.println("Error while filtering the agent claim data"+e.getMessage());
		}
		
		
		
		
		return showClaimDetailsList;
	}

}
