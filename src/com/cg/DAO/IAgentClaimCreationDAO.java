package com.cg.DAO;

import java.util.List;

import com.cg.dto.Claim;
import com.cg.dto.Policy;

public interface IAgentClaimCreationDAO {
	public List<Policy> getclaimDetails(String agentUserName) throws Exception;
	public List<Policy> getclaimDetails(String agentUserName,String clientUserName) throws Exception;
	public int registerClaim(Claim claim) throws Exception;
}
