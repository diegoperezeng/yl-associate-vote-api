package com.diegoperezeng.associatesvotes.controllers.config;

import javax.validation.constraints.NotBlank;

import com.diegoperezeng.associatesvotes.validation.ValidCpf;

public class AssociatePost {
	
	@NotBlank
	@ValidCpf
	private String cpf;
	
	@NotBlank
	private String name;
	
	@NotBlank
	private String email;	
	
    public AssociatePost(@NotBlank @ValidCpf String cpf, @NotBlank String name, @NotBlank String email) {
		super();
		this.cpf = cpf;
		this.name = name;
		this.email = email;
	}

	public AssociatePost() {
      // This class is used to receive the POST request body
    }
	
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
