package com.cg.DAO;

import java.util.List;

import com.cg.dto.ShowClaimDetails;

public interface IAgentSearchBasedOnFilterDAO {
	List<ShowClaimDetails> getClaimDetails(String username,String filter,int accountNumber) throws Exception;
}
