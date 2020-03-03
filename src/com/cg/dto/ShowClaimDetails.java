package com.cg.dto;

public class ShowClaimDetails {
	private int policyNumber;
	private int policyPremium;
	private int accountNumber;
	private String policyType;
	private int claimNumber;
	private String status;
	

	public ShowClaimDetails() {
		super();
	}

	
	public ShowClaimDetails(int policyNumber, int policyPremium, int accountNumber, String policyType,
			int claimNumber,String status) {
		super();
		this.policyNumber = policyNumber;
		this.policyPremium = policyPremium;
		this.accountNumber = accountNumber;
		this.policyType = policyType;
		this.claimNumber = claimNumber;
		this.status = status;
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

	public int getClaimNumber() {
		return claimNumber;
	}

	public void setClaimNumber(int claimNumber) {
		this.claimNumber = claimNumber;
	}

	
	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	@Override
	public String toString() {
		return "ShowClaimDetails [policyNumber=" + policyNumber + ", policyPremium=" + policyPremium
				+ ", accountNumber=" + accountNumber + ", policyType=" + policyType + ", claimNumber=" + claimNumber
				+ ", status=" + status + "]";
	}


	
	
}
