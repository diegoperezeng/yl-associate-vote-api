package com.diegoperezeng.associatesvotes.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;


@Entity
@Table(name = "vote")
public class Vote implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long sectionId;
	private Long associateId;
	private VoteOption voteChoice;
	private Timestamp createdAt;
	
	// Vote options
    public enum VoteOption {
        yes, no
    }
    
	public Vote() {
	}
	
	public Vote(Long id, Long sectionId, Long associateId, VoteOption voteChoice, Timestamp createdAt) {
		super();
		this.id = id;
		this.sectionId = sectionId;
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

	public Long getSectionId() {
		return sectionId;
	}

	public void setSectionId(Long sectionId) {
		this.sectionId = sectionId;
	}

	public Long getAssociateId() {
		return associateId;
	}

	public void setAssociateId(Long associateId) {
		this.associateId = associateId;
	}

	public VoteOption getVoteChoice() {
		return voteChoice;
	}

	public void setVoteChoice(VoteOption voteChoice) {
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
	
	