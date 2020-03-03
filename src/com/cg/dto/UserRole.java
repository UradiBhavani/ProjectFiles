package com.cg.dto;

public class UserRole {
	private String userName;
	private String password;
	private String roleCode;
	private String agentId;
	private int accountNumber;
	public UserRole() {
		super();
	}
	public UserRole(String userName, String password, String roleCode, String agentId, int accountNumber) {
		super();
		this.userName = userName;
		this.password = password;
		this.roleCode = roleCode;
		this.agentId = agentId;
		this.accountNumber = accountNumber;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	public String getAgentId() {
		return agentId;
	}
	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}
	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	@Override
	public String toString() {
		return "UserRole [userName=" + userName + ", password=" + password + ", roleCode=" + roleCode + ", agentId="
				+ agentId + ", accountNumber=" + accountNumber + "]";
	}
	
	
}