package com.cg.exceptions;

import java.sql.SQLException;

public class OracleException extends SQLException{
	public OracleException(String message) {
		super(message);
		
	}
}
