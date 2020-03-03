package com.cg.DAO;

import java.util.ArrayList;

import com.cg.dto.Claim;
import com.cg.exceptions.InsuranceException;

public interface IGetClaimDetails {
	public Claim getClaim(int claimNumber) throws Exception;
}
