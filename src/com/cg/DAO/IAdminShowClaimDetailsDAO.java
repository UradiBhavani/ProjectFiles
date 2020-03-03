package com.cg.DAO;

import java.util.List;

import com.cg.dto.ShowClaimDetails;

public interface IAdminShowClaimDetailsDAO {
	List<ShowClaimDetails> getClaimDetails() throws Exception;
}
