package com.cg.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.cg.dto.ShowClaimDetails;
import com.cg.utility.JDBCUtility;

public class AdminShowClaimDetailsDAOImpl implements IAdminShowClaimDetailsDAO{

	
	Connection connection = null;
	PreparedStatement statement = null;
	
	
	@Override
	public List<ShowClaimDetails> getClaimDetails() throws Exception {
		List<ShowClaimDetails> showClaimDetailsList = new ArrayList<ShowClaimDetails>();
		connection = JDBCUtility.getConnection();
		
		try {
			System.out.println("In admin try");
			String queryAccountNum = "select accountnumber from userrole where rolecode<>'adm' and rolecode<>'agnt'";
			statement=connection.prepareStatement(queryAccountNum);
			//statement.setString(1, username);
		
			ResultSet res = statement.executeQuery();
			
			
			List<Integer> accountNumber = new ArrayList<>();
			while(res.next()) {
				System.out.println("In acc while");
				accountNumber.add(res.getInt("accountNumber"));
			}
			
			
			int i = 0;
			System.out.println("Account number : "+accountNumber);
			
			
			while (i < accountNumber.size()) {
				String query = "select p.accountNumber,p.policyNumber,c.claimNumber,p.policyPremium,p.policyType from policy p, claim c where p.accountNumber=? and p.policyNumber=c.policyNumber";
				statement = connection.prepareStatement(query);
				statement.setInt(1, accountNumber.get(i));
				res = statement.executeQuery();
				while (res.next()) {
					//System.out.println("In while");
					ShowClaimDetails claimDetails = new ShowClaimDetails();
					claimDetails.setAccountNumber(res.getInt("accountNumber"));
					claimDetails.setPolicyNumber(res.getInt("policyNumber"));
					claimDetails.setPolicyPremium(res.getInt("policyPremium"));
					claimDetails.setPolicyType(res.getString("policyType"));
					claimDetails.setClaimNumber(res.getInt("claimNumber"));
					//claimDetails.setStatus(res.getString("status"));
					showClaimDetailsList.add(claimDetails);
				} 
				i++;
			}
			
			
	
			System.out.println("Size = "+showClaimDetailsList.size());
		}catch(Exception e) {
			System.out.println(e.getMessage()+"error in select query");
		}finally {
			connection.close();
		}
		return showClaimDetailsList;
	}

}
