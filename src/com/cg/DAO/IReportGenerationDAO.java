package com.cg.DAO;

import java.util.List;

import com.cg.dto.ShowClaimDetails;

public interface IReportGenerationDAO {
	List<ShowClaimDetails> getClaimDetails() throws Exception;
	List<ShowClaimDetails> getSearchedClaimDetails(int accountNumber,String filter) throws Exception;
}
