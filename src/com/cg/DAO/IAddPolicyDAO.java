package com.cg.DAO;


import com.cg.dto.Policy;
import com.cg.exceptions.InsuranceException;

public interface IAddPolicyDAO {
	public int addPolicy(Policy policy) throws InsuranceException;

}
