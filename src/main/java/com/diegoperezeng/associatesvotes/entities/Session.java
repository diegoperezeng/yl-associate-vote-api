package com.diegoperezeng.associatesvotes.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
@Table(name = "session")
public class Session implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long topicId;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime startTime;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime endTime;
	private Boolean isOpen;
	private Integer voteCountYes;
	private Integer voteCountNo;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdAt;
	
    
	public Session() {
	}


	public Session(Long id, Long topicId, LocalDateTime startTime, LocalDateTime endTime, Boolean isOpen, Integer voteCountYes,
			Integer voteCountNo) {
		super();
		this.id = id;
		this.topicId = topicId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.isOpen = isOpen;
		this.voteCountYes = voteCountYes;
		this.voteCountNo = voteCountNo;
		this.createdAt = LocalDateTime.now();
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Long getTopicId() {
		return topicId;
	}


	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}


	public LocalDateTime getStartTime() {
		return startTime;
	}


	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}


	public LocalDateTime getEndTime() {
		return endTime;
	}


	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}


	public Boolean getIsOpen() {
		return isOpen;
	}


	public void setIsOpen(Boolean isOpen) {
		this.isOpen = isOpen;
	}


	public Integer getVoteCountYes() {
		return voteCountYes;
	}


	public void setVoteCountYes(Integer voteCountYes) {
		this.voteCountYes = voteCountYes;
	}


	public Integer getVoteCountNo() {
		return voteCountNo;
	}


	public void setVoteCountNo(Integer voteCountNo) {
		this.voteCountNo = voteCountNo;
	}


	public LocalDateTime getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public int hashCode() {
		return Objects.hash(createdAt, id, voteCountYes);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Session other = (Session) obj;
		return Objects.equals(createdAt, other.createdAt) && Objects.equals(id, other.id)
				&& Objects.equals(voteCountYes, other.voteCountYes);
	}
	
	

}
