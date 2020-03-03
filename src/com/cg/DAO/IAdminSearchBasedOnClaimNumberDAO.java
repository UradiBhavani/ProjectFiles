package com.cg.DAO;

import java.sql.SQLException;
import java.util.List;

import com.cg.dto.ShowClaimDetails;
import com.cg.exceptions.InsuranceException;
import com.cg.exceptions.OracleException;

public interface IAdminSearchBasedOnClaimNumberDAO {
	List<ShowClaimDetails> getClaimDetails(int claimNumber) throws InsuranceException, SQLException;
}