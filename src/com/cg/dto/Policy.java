package com.cg.dto;

public class Policy {
	private int policyNumber;
	private int policyPremium;
	private int accountNumber;
	private String policyType;
	public Policy() {
		
	}
	
	public Policy(int policyNumber, int policyPremium, int accountNumber, String policyType) {
		super();
		this.policyNumber = policyNumber;
		this.policyPremium = policyPremium;
		this.accountNumber = accountNumber;
		this.policyType = policyType;
	}
	public int getPolicyNumber() {
		return policyNumber;
	}
	public void setPolicyNumber(int policyNumber) {
		this.policyNumber = policyNumber;
	}
	public int getPolicyPremium() {
		return policyPremium;
	}
	public void setPolicyPremium(int policyPremium) {
		this.policyPremium = policyPremium;
	}
	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getPolicyType() {
		return policyType;
	}

	public void setPolicyType(String policyType) {
		this.policyType = policyType;
	}

	@Override
	public String toString() {
		return "Policy [policyNumber=" + policyNumber + ", policyPremium=" + policyPremium + ", accountNumber="
				+ accountNumber + ", policyType=" + policyType + "]";
	}
	
	
	
}