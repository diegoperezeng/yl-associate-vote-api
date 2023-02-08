package com.diegoperezeng.associatesvotes.resources.config;

public class TopicPost {

	private String title;
	private String description;
	private Boolean openStatus;	
	    
	public TopicPost() {
	}

	public TopicPost(String title, String description, Boolean openStatus) {
		super();
		this.title = title;
		this.description = description;
		this.openStatus = openStatus;
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
}
