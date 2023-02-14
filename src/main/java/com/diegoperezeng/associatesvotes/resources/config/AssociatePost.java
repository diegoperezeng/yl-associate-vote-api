package com.diegoperezeng.associatesvotes.resources.config;

public class AssociatePost {
	
	private String cpf;
	private String name;
	private String email;	
	
    public AssociatePost() {
    }
	
    // public AssociatePost(String cpf, String name, String email) {
    //     super();
    //     this.cpf = cpf;
    //     this.name = name;
    //     this.email = email;
    // }
	
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}	
}
