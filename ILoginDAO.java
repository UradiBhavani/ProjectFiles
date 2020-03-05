package com.cg.DAO;

import java.sql.SQLException;


public interface ILoginDAO {
	public int validate(String username,String password) throws SQLException, Exception;
	public String getRoleCode(String username) throws SQLException, Exception;
	public int resetPassword(String username,String newPassword) throws Exception;

}
