package com.diegoperezeng.associatesvotes.entities;

import javax.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
@Table(name = "topic")
public class Topic implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String description;
	private Boolean openStatus;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
	private Timestamp createdAt;
	    
	public Topic() {
	}

	public Topic(Long id, String title, String description, Boolean openStatus, Timestamp createdAt) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.openStatus = openStatus;
		this.createdAt = createdAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getOpenStatus() {
		return openStatus;
	}

	public void setOpenStatus(Boolean openStatus) {
		this.openStatus = openStatus;
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
		Topic other = (Topic) obj;
		return Objects.equals(createdAt, other.createdAt) && Objects.equals(id, other.id);
	}
	
	
	
}
