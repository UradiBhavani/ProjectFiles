package com.cg.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cg.dto.ShowClaimDetails;
import com.cg.exceptions.InsuranceException;
import com.cg.exceptions.OracleException;
import com.cg.utility.JDBCUtility;

public class AdminSearchBasedOnClaimNumberDAOImpl implements IAdminSearchBasedOnClaimNumberDAO{

	Connection connection = null;
	PreparedStatement statement = null;
	
	
	@Override
	public List<ShowClaimDetails> getClaimDetails(int claimNumber) throws InsuranceException, SQLException {
		List<ShowClaimDetails> showClaimDetailsList = new ArrayList<ShowClaimDetails>();
		connection = JDBCUtility.getConnection();
		
		
		try {
			ResultSet res;
			
			
			String query = "select p.accountNumber,p.policyNumber,c.claimNumber,p.policyPremium,p.policyType from policy p, claim c where c.claimNumber=? and p.policyNumber=c.policyNumber";
			//String query = "select p.accountNumber,p.policymber,c.claimNumber,p.policyPremium,p.policyType from policy p, claim c where p.policyNumber=c.policyNumber";
			statement = connection.prepareStatement(query);
			statement.setInt(1, claimNumber);
			res = statement.executeQuery();
			while (res.next()) {
				System.out.println("In admin search while");
				ShowClaimDetails claimDetails = new ShowClaimDetails();
				claimDetails.setAccountNumber(res.getInt("accountNumber"));
				claimDetails.setPolicyNumber(res.getInt("policyNumber"));
				claimDetails.setPolicyPremium(res.getInt("policyPremium"));
				claimDetails.setPolicyType(res.getString("policyType"));
				claimDetails.setClaimNumber(res.getInt("claimNumber"));
				//claimDetails.setStatus(res.getString("status"));
				showClaimDetailsList.add(claimDetails);
			} 
			
			
			
			
	
			System.out.println("Size = "+showClaimDetailsList.size());
		}catch(SQLException e) {
			throw new InsuranceException("errorwhile getting data from the database ");
		}finally {
		
				connection.close();
		}
		return showClaimDetailsList;
		
		
	}

}
