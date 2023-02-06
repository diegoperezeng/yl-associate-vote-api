package com.diegoperezeng.associatesvotes.entities;

import javax.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
@Table(name = "vote")
public class Vote implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long sessionId;
	private Long associateId;
	private Boolean voteChoice;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
	private Timestamp createdAt;
	
	    
	public Vote() {
	}
	
	public Vote(Long id, Long sessionId, Long associateId, Boolean voteChoice, Timestamp createdAt) {
		super();
		this.id = id;
		this.sessionId = sessionId;
		this.associateId = associateId;
		this.voteChoice = voteChoice;
		this.createdAt = createdAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
		return Objects.hash(createdAt, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vote other = (Vote) obj;
		return Objects.equals(createdAt, other.createdAt) && Objects.equals(id, other.id);
	}
	
	
}
	
	