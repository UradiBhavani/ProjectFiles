package com.cg.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.cg.dto.ShowClaimDetails;
import com.cg.utility.JDBCUtility;

public class ReportGenerationDAOImpl implements IReportGenerationDAO{

	Connection connection=null;
	PreparedStatement statement = null;
	
	@Override
	public List<ShowClaimDetails> getClaimDetails() throws Exception {
		List<ShowClaimDetails> showClaimDetailsList = new ArrayList<ShowClaimDetails>();
		connection = JDBCUtility.getConnection();
		
		try {
			String queryAccountNum = "select accountnumber from userrole where rolecode<>'adm' and rolecode<>'agnt'";
			statement=connection.prepareStatement(queryAccountNum);
			

			ResultSet res = statement.executeQuery();
			
			
			List<Integer> accountNumber = new ArrayList<>();
			while(res.next()) {
				System.out.println("In acc while");
				accountNumber.add(res.getInt("accountNumber"));
			}
			
			
			int i = 0;
			System.out.println("Account number : "+accountNumber);
			
			
			while (i < accountNumber.size()) {
				String query = "select p.accountNumber,p.policyNumber,c.claimNumber,p.policyPremium,p.policyType,c.status  from policy p, claim c where p.accountNumber=? and p.policyNumber=c.policyNumber";
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
					claimDetails.setStatus(res.getString("status"));
					showClaimDetailsList.add(claimDetails);
				} 
				i++;
			}
			
			

			System.out.println("Size = "+showClaimDetailsList.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return showClaimDetailsList;
		
		
		
	}

	@Override
	public List<ShowClaimDetails> getSearchedClaimDetails(int accountNumber,String filter) throws Exception {
		List<ShowClaimDetails> showClaimDetailsList = new ArrayList<ShowClaimDetails>();
		connection = JDBCUtility.getConnection();
		
		try {
			String query="";
			ResultSet res=null;
			if(filter.equals("all")) {
				query = "select p.accountNumber,p.policyNumber,c.claimNumber,p.policyPremium,p.policyType,c.status  from policy p, claim c where p.accountNumber=? and p.policyNumber=c.policyNumber";
				statement = connection.prepareStatement(query);
				statement.setInt(1, accountNumber);
				
			}else {
				query = "select p.accountNumber,p.policyNumber,c.claimNumber,p.policyPremium,p.policyType,c.status  from policy p, claim c where p.accountNumber=? and c.status=? and p.policyNumber=c.policyNumber";
				statement = connection.prepareStatement(query);
				statement.setInt(1, accountNumber);
				statement.setString(2, filter);
			}
			
			System.out.println("AC= "+accountNumber+ "    ----     Filter = "+filter);
			res = statement.executeQuery();
			
			while (res.next()) {
				//System.out.println("In while");
				ShowClaimDetails claimDetails = new ShowClaimDetails();
				claimDetails.setAccountNumber(res.getInt("accountNumber"));
				claimDetails.setPolicyNumber(res.getInt("policyNumber"));
				claimDetails.setPolicyPremium(res.getInt("policyPremium"));
				claimDetails.setPolicyType(res.getString("policyType"));
				claimDetails.setClaimNumber(res.getInt("claimNumber"));
				claimDetails.setStatus(res.getString("status"));
				showClaimDetailsList.add(claimDetails);
				 
				
			}
			
			

			System.out.println("Report generation screen Size = "+showClaimDetailsList.size());
		} catch (Exception e) {
			System.out.println("Error in search report generation screen");
		}
		return showClaimDetailsList;
	}
	

}
