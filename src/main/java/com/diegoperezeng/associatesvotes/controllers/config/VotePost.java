package com.diegoperezeng.associatesvotes.controllers.config;

public class VotePost {

	private Long sessionId;
	private Long associateId;
	private Boolean voteChoice;	
	    
	public VotePost() {
	}
	
	public VotePost(Long sessionId, Long associateId, Boolean voteChoice) {
		super();
		this.sessionId = sessionId;
		this.associateId = associateId;
		this.voteChoice = voteChoice;
	}

	public Long getSessionId() {
		return sessionId;
	}

	public void setSessionId(Long sessionId) {
		this.sessionId = sessionId;
	}

	public Long getAssociateId() {
		return associateId;
	}

	public void setAssociateId(Long associateId) {
		this.associateId = associateId;
	}

	public Boolean getVoteChoice() {
		return voteChoice;
	}

	public void setVoteChoice(Boolean voteChoice) {
		this.voteChoice = voteChoice;
	}
}
	
	