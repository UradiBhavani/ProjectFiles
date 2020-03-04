package com.cg.DAO;

import java.sql.SQLException;
import java.util.List;

import com.cg.dto.Policy;
import com.cg.dto.PolicyDetails;
import com.cg.dto.Questions;
import com.cg.dto.ShowClaimDetails;
import com.cg.dto.UserRole;
import com.cg.exceptions.InsuranceException;

public interface IAdminInterfaceDAO {
	
	public int createProfile(UserRole userRole) throws Exception;
	
	public int addPolicy(Policy policy) throws InsuranceException;
	
	//List<ShowClaimDetails> getClaimDetails(int claimNumber) throws InsuranceException, SQLException;

	//List<ShowClaimDetails> getClaimDetails() throws Exception;
	
	List<ShowClaimDetails> getClaimDetailsForReportGeneration() throws Exception;
	
	List<ShowClaimDetails> getSearchedClaimDetails(int accountNumber,String filter) throws Exception;
	
	public int setStatusofClaim(int claimNumber, String status) throws Exception;
	
	public boolean checkPolicy(String policyType,int accountNumber) throws Exception;
	
	public boolean checkProfile(String username,int accountNumber) throws Exception;
	
	public List<String> getAgents() throws Exception;
	
	
}
