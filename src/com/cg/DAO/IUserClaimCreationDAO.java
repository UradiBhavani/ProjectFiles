package com.cg.DAO;

import java.util.List;

import com.cg.dto.Claim;
import com.cg.dto.Policy;

public interface IUserClaimCreationDAO {
	public List<Policy> getclaimDetails(String username) throws Exception;
	public int registerClaim(Claim claim) throws Exception;

}
