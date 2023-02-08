package com.diegoperezeng.associatesvotes.services.config;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TopicResult {
	
	
	private String title;
	
	@JsonProperty("numero_da_pauta")
	private Long topicId;
	
	@JsonProperty("Votos_a_favor")
	private Integer voteCountYes;
	
	@JsonProperty("Votos_contrarios")
	private Integer voteCountNo;
	
	public TopicResult() {
	}
	
	public TopicResult(String title, Long topicId, Integer voteCountYes) {
		this.title = title;
		this.topicId = topicId;
		this.voteCountYes = voteCountYes;
	}

	public Long getTopicId() {
		return topicId;
	}

	public Integer getVoteCountYes() {
		return voteCountYes;
	}
	
	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}

	public void setVoteCountYes(Integer voteCountYes) {
		this.voteCountYes = voteCountYes;
	}

	public TopicResult(String title) {
		super();
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getVoteCountNo() {
		return voteCountNo;
	}

	public void setVoteCountNo(Integer voteCountNo) {
		this.voteCountNo = voteCountNo;
	}
}
