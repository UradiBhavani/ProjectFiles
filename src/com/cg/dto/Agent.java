package com.cg.dto;

public class Agent {
	private String agentId;
	private String agentName;
	public Agent() {
		super();
	}
	public Agent(String agentId, String agentName) {
		super();
		this.agentId = agentId;
		this.agentName = agentName;
	}
	public String getAgentId() {
		return agentId;
	}
	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}
	public String getAgentName() {
		return agentName;
	}
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	@Override
	public String toString() {
		return "Agent [agentId=" + agentId + ", agentName=" + agentName + "]";
	}
	
	
}