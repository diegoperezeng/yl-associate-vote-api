package com.diegoperezeng.associatesvotes.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;


@Entity
@Table(name = "section")
public class Section implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long topicId;
	private Timestamp startTime;
	private Timestamp endTime;
	private Boolean isOpen;
	private Integer voteCountYes;
	private Integer voteCountNo;
	private Timestamp createdAt;
	
    
	public Section() {
	}


	public Section(Long id, Long topicId, Timestamp startTime, Timestamp endTime, Boolean isOpen, Integer voteCountYes,
			Integer voteCountNo, Timestamp createdAt) {
		super();
		this.id = id;
		this.topicId = topicId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.isOpen = isOpen;
		this.voteCountYes = voteCountYes;
		this.voteCountNo = voteCountNo;
		this.createdAt = createdAt;
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


	public Timestamp getStartTime() {
		return startTime;
	}


	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}


	public Timestamp getEndTime() {
		return endTime;
	}


	public void setEndTime(Timestamp endTime) {
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


	public Timestamp getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(Timestamp createdAt) {
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
		Section other = (Section) obj;
		return Objects.equals(createdAt, other.createdAt) && Objects.equals(id, other.id)
				&& Objects.equals(voteCountYes, other.voteCountYes);
	}
	
	

}
