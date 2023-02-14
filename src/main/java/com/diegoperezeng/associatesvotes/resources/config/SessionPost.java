package com.diegoperezeng.associatesvotes.resources.config;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class SessionPost {
	
	private Long topicId;
	
	private LocalDateTime startTime;
	
	private LocalDateTime endTime;
	
	private Boolean isOpen;
	
	public SessionPost() {
	}

	// public SessionPost(Long topicId, LocalDateTime startTime, LocalDateTime endTime, Boolean isOpen) {
	// 	super();
	// 	this.topicId = topicId;
	// 	this.startTime = startTime;
	// 	this.endTime = endTime;
	// 	this.isOpen = isOpen;
	// }

	public Long getTopicId() {
		return topicId;
	}

	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	public LocalDateTime getStartTime() {
		return startTime;
	}

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	public LocalDateTime getEndTime() {
		return endTime;
	}

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}
	
	public Boolean getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(Boolean isOpen) {
		this.isOpen = isOpen;
	}
}