package com.cg.DAO;

import java.util.List;

import com.cg.dto.ShowClaimDetails;

public interface IUserShowClaimDetailsDAO {
	List<ShowClaimDetails> getClaimDetails(String username) throws Exception;
}
