package com.cg.DAO;

import com.cg.dto.UserRole;

public interface IProfileCreationDAO {
	public int createProfile(UserRole userRole) throws Exception;
}
