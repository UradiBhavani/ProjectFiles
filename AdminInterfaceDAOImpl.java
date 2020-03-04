package com.cg.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.cg.controller.LoginServlet;
import com.cg.dto.Policy;
import com.cg.dto.PolicyDetails;
import com.cg.dto.Questions;
import com.cg.dto.ShowClaimDetails;
import com.cg.dto.UserRole;
import com.cg.exceptions.InsuranceException;
import com.cg.queries.QueryConstants;
import com.cg.queries.QueryConstraints;
import com.cg.utility.JDBCUtility;

public class AdminInterfaceDAOImpl implements IAdminInterfaceDAO{
	
	
	Connection connection = null;
	PreparedStatement statement = null;
	
	static final Logger LOGGER = Logger.getLogger(AdminInterfaceDAOImpl.class);
	
	@Override
	public int createProfile(UserRole userRole) throws Exception {
		connection = JDBCUtility.getConnection();
		int res=0;
		try {
			
			LOGGER.info("Inside the create profile method in AdminDAO Implementation");
			
			
			String query=QueryConstants.ADD_USERROLE;
			statement = connection.prepareStatement(query);
			statement.setString(1, userRole.getUserName());
			statement.setString(2, userRole.getPassword());
			statement.setString(3, userRole.getRoleCode());
			statement.setString(4, userRole.getAgentId());
			statement.setInt(5, userRole.getAccountNumber());
			
			
			res = statement.executeUpdate();
			
		} catch (Exception e) {
			
			LOGGER.info("Error while creating profile for the user in Admin DAO ");
		}
		
		
		return res;
	}
	
	public boolean checkProfile(String username,int accountNumber) throws Exception {
		connection = JDBCUtility.getConnection();
		
		try {
			LOGGER.info("Inside check user method in AdminDAO Implementation");
			
			
			String query = QueryConstants.CHECK_USER;
			statement = connection.prepareStatement(query);
			statement.setString(1, username);
			statement.setInt(2, accountNumber);
			
			ResultSet res = statement.executeQuery();
			
			if(res.next()) {
				return true;
			}else {
				return false;
			}
			
		}catch(Exception e){
			LOGGER.error("Error while fetching the data in check user method");
		}
		
		
		return false;
		
	}
	
	
	
	@Override
	public int addPolicy(Policy policy) throws InsuranceException {
		connection = JDBCUtility.getConnection();
		
		int res=0;
		try {
			
			LOGGER.info("Inside Add policy method in Admin DAO Implementation");
			
			
			
			String query = QueryConstants.ADD_POLICY;
			statement = connection.prepareStatement(query);
			
			statement.setInt(1,policy.getPolicyNumber());
			statement.setInt(2,policy.getPolicyPremium());
			statement.setInt(3, policy.getAccountNumber());
			statement.setString(4, policy.getPolicyType());
			
			res = statement.executeUpdate();
			
			
			
			
		} catch (Exception e) {
			
			LOGGER.error("Error while inserting data into the database in Add Policy method");
			
		}
		
		
		return res;
	}
	
	
	public boolean checkPolicy(String policyType,int accountNumber) throws Exception {
		connection = JDBCUtility.getConnection();
		
		try {
			LOGGER.info("Inside check policy method in AdminDAO Implementation");
			
			
			String query = QueryConstants.CHECK_POLICY;
			statement = connection.prepareStatement(query);
			statement.setString(1, policyType);
			statement.setInt(2, accountNumber);
			
			ResultSet res = statement.executeQuery();
			
			if(res.next()) {
				return true;
			}else {
				return false;
			}
			
		}catch(Exception e) {
			LOGGER.error("Error while fetching the data");
		}
		
		return false;
		
	}
	
	
	
	/*@Override
	public List<ShowClaimDetails> getClaimDetails(int claimNumber) throws InsuranceException, SQLException {
		List<ShowClaimDetails> showClaimDetailsList = new ArrayList<ShowClaimDetails>();
		connection = JDBCUtility.getConnection();
		
		
		try {
			LOGGER.info("Inside the get claim details using claim number method in AdminDAO Implementation");
			
			ResultSet res;
			
			String query = QueryConstraints.GETCLAIMDETAILSFILTERADMIN;
			
			statement = connection.prepareStatement(query);
			statement.setInt(1, claimNumber);
			res = statement.executeQuery();
			
			while (res.next()) {
				
				ShowClaimDetails claimDetails = new ShowClaimDetails();
				
				claimDetails.setAccountNumber(res.getInt("accountNumber"));
				claimDetails.setPolicyNumber(res.getInt("policyNumber"));
				claimDetails.setPolicyPremium(res.getInt("policyPremium"));
				claimDetails.setPolicyType(res.getString("policyType"));
				claimDetails.setClaimNumber(res.getInt("claimNumber"));
				showClaimDetailsList.add(claimDetails);
				
			} 
			
		}catch(SQLException e) {
			
			LOGGER.error("Error while retriving the claim details in Admin DAO Implementation");
			
		}finally {
		
				connection.close();
		}
		return showClaimDetailsList;
		
		
	}*/
	
	
	/*@Override
	public List<ShowClaimDetails> getClaimDetails() throws Exception {
		List<ShowClaimDetails> showClaimDetailsList = new ArrayList<ShowClaimDetails>();
		connection = JDBCUtility.getConnection();
		
		try {
			
			LOGGER.info("Inside the get claim details using method in AdminDAO Implementation");
			
			String queryAccountNum = QueryConstraints.GET_ACCOUNTNUMBER_LIST;
			statement=connection.prepareStatement(queryAccountNum);
		
			ResultSet res = statement.executeQuery();
			
			List<Integer> accountNumber = new ArrayList<>();
			
			while(res.next()) {
				
				accountNumber.add(res.getInt("accountNumber"));
				
			}
			
			
			int i = 0;			
			
			while (i < accountNumber.size()) {
				
				String query = "select p.accountNumber,p.policyNumber,c.claimNumber,p.policyPremium,p.policyType from policy p, claim c where p.accountNumber=? and p.policyNumber=c.policyNumber";
				statement = connection.prepareStatement(query);
				statement.setInt(1, accountNumber.get(i));
				res = statement.executeQuery();
				while (res.next()) {
					
					ShowClaimDetails claimDetails = new ShowClaimDetails();
					
					claimDetails.setAccountNumber(res.getInt("accountNumber"));
					claimDetails.setPolicyNumber(res.getInt("policyNumber"));
					claimDetails.setPolicyPremium(res.getInt("policyPremium"));
					claimDetails.setPolicyType(res.getString("policyType"));
					claimDetails.setClaimNumber(res.getInt("claimNumber"));
					showClaimDetailsList.add(claimDetails);
					
				} 
				i++;
			}
			
		}catch(Exception e) {
			
			LOGGER.error("Error while retriving the claim details in Admin DAO Implementation");
			
		}finally {
			
			connection.close();
			
		}
		return showClaimDetailsList;
	}*/
	
	
	
	
	@Override
	public List<ShowClaimDetails> getClaimDetailsForReportGeneration() throws Exception {
		List<ShowClaimDetails> showClaimDetailsList = new ArrayList<ShowClaimDetails>();
		connection = JDBCUtility.getConnection();
		
		try {
			
			LOGGER.info("Inside the get Claim Details For Report Generation method in AdminDAO Implementation");
			
			
			String queryAccountNum = QueryConstants.GET_ACCOUNTNUMBER_LIST_IN_ADMIN;
			statement=connection.prepareStatement(queryAccountNum);
			
			ResultSet res = statement.executeQuery();
			
			List<Integer> accountNumber = new ArrayList<>();
			while(res.next()) {
				
				accountNumber.add(res.getInt("accountNumber"));
				
			}
			
			
			int i = 0;			
			
			while (i < accountNumber.size()) {
				
				String query = QueryConstants.DISPLAY_CLAIM_DETAILS;
				statement = connection.prepareStatement(query);
				statement.setInt(1, accountNumber.get(i));
				
				res = statement.executeQuery();
				
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
				i++;
			}
			
			
		} catch (Exception e) {
			
			LOGGER.error("Error while retriving the details for Report generation for AdminDAO Implementation");
		}
		return showClaimDetailsList;
		
		
		
	}

	@Override
	public List<ShowClaimDetails> getSearchedClaimDetails(int accountNumber,String filter) throws Exception {
		List<ShowClaimDetails> showClaimDetailsList = new ArrayList<ShowClaimDetails>();
		connection = JDBCUtility.getConnection();
		
		try {
			
			LOGGER.info("Inside the Searched Claim Details method based on account number and filter in AdminDAO Implementation");
			
			String query="";
			ResultSet res=null;
			
			if(filter.equals("all")) {
				
				query = QueryConstants.DISPLAY_CLAIM_DETAILS;
				statement = connection.prepareStatement(query);
				statement.setInt(1, accountNumber);
				
			}else {
				
				query = QueryConstants.DISPLAY_CLAIM_DETAILS_USING_STATUS;
				statement = connection.prepareStatement(query);
				statement.setInt(1, accountNumber);
				statement.setString(2, filter);
				
			}
			
			res = statement.executeQuery();
			
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
			
			
		} catch (Exception e) {
			
			LOGGER.error("Error while searching the data for report generation in AdminDAO Implementation");
			
		}
		return showClaimDetailsList;
	}
	
	
	@Override
	public int setStatusofClaim(int claimNumber,String status) throws Exception {
		connection = JDBCUtility.getConnection();
		int res=0;
		
		try {
			
			LOGGER.info("Inside the set status of claim method in AdminDAO Implementation");
			
			String query = QueryConstants.UPDATE_STATUS;
			
			statement = connection.prepareStatement(query);
			statement.setString(1, status);
			statement.setInt(2, claimNumber);
			
			res = statement.executeUpdate();
			
			
		} catch (Exception e) {
			
			LOGGER.error("Error while updating the status of claim in AdminDAO Implementation");	
		}finally {
			connection.commit();
			connection.close();
		}
		System.out.println("id = "+res);
		return res;
		
	}
	
	public List<String> getAgents() throws InsuranceException{
		connection = JDBCUtility.getConnection();
		List<String> agentList = new ArrayList<String>();
		
		try {
			
			LOGGER.info("Inside get agents method in AdminDAO Implementation");
			
			String query = "SELECT AGENTID FROM USERROLE";
			statement = connection.prepareStatement(query);
			
			ResultSet res = statement.executeQuery();
			while(res.next()) {
				agentList.add(res.getString("agentId"));
			}
		} catch (SQLException e) {
			
			LOGGER.error("Error while fetching the agents from DB");
			
		}
		return agentList;
		
	}
	
}
