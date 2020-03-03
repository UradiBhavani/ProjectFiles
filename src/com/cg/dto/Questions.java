package com.cg.dto;

public class Questions {
	private String questionId;
	private String question;
	private String policyType;
	
	public Questions() {
		super();
	}

	public Questions(String questionId, String question,String policyType) {
		super();
		this.questionId = questionId;
		this.question = question;
		this.policyType=policyType;
	}

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getPolicyType() {
		return policyType;
	}

	public void setPolicyType(String policyType) {
		this.policyType = policyType;
	}

	@Override
	public String toString() {
		return "Questions [questionId=" + questionId + ", question=" + question + ", policyType=" + policyType + "]";
	}
	
	
	
}