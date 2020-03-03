package com.cg.DAO;

import java.util.List;

import com.cg.dto.ShowClaimDetails;

public interface IUserSearchBasedOnFilterDAO {
	List<ShowClaimDetails> getClaimDetails(String username,String filter) throws Exception;

}
