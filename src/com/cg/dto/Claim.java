package com.cg.dto;

public class Claim {
	private int claimNumber;
	private String claimReason;
	private String accidentLocationStreet;
	private String accidentCity;
	private String accidentState;
	private int accidentZip;
	private String claimType;
	private int policyNumber;
	private String userName;
	private String status;
	public Claim() {
		super();
	}
	public Claim(int claimNumber, String claimReason, String accidentLocationStreet, String accidentCity,
			String accidentState, int accidentZip, String claimType, int policyNumber, String userName) {
		super();
		this.claimNumber = claimNumber;
		this.claimReason = claimReason;
		this.accidentLocationStreet = accidentLocationStreet;
		this.accidentCity = accidentCity;
		this.accidentState = accidentState;
		this.accidentZip = accidentZip;
		this.claimType = claimType;
		this.policyNumber = policyNumber;
		this.userName = userName;
		this.status = "pending";
	}
	public int getClaimNumber() {
		return claimNumber;
	}
	public void setClaimNumber(int claimNumber) {
		this.claimNumber = claimNumber;
	}
	public String getClaimReason() {
		return claimReason;
	}
	public void setClaimReason(String claimReason) {
		this.claimReason = claimReason;
	}
	public String getAccidentLocationStreet() {
		return accidentLocationStreet;
	}
	public void setAccidentLocationStreet(String accidentLocationStreet) {
		this.accidentLocationStreet = accidentLocationStreet;
	}
	public String getAccidentCity() {
		return accidentCity;
	}
	public void setAccidentCity(String accidentCity) {
		this.accidentCity = accidentCity;
	}
	public String getAccidentState() {
		return accidentState;
	}
	public void setAccidentState(String accidentState) {
		this.accidentState = accidentState;
	}
	public int getAccidentZip() {
		return accidentZip;
	}
	public void setAccidentZip(int accidentZip) {
		this.accidentZip = accidentZip;
		System.out.println(accidentZip);
	}
	public String getClaimType() {
		return claimType;
	}
	public void setClaimType(String claimType) {
		this.claimType = claimType;
	}
	public int getPolicyNumber() {
		return policyNumber;
	}
	public void setPolicyNumber(int policyNumber) {
		this.policyNumber = policyNumber;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus() {
		this.status = "pending";
	}
	@Override
	public String toString() {
		return "Claim [claimNumber=" + claimNumber + ", claimReason=" + claimReason + ", accidentLocationStreet="
				+ accidentLocationStreet + ", accidentCity=" + accidentCity + ", accidentState=" + accidentState
				+ ", accidentZip=" + accidentZip + ", claimType=" + claimType + ", policyNumber=" + policyNumber
				+ ", userName=" + userName + ", status=" + status + "]";
	}


	
	
	

}