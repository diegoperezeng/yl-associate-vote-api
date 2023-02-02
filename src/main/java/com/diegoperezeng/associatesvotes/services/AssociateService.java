package com.diegoperezeng.associatesvotes.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.diegoperezeng.associatesvotes.entities.Associate;
import com.diegoperezeng.associatesvotes.repositories.AssociateRepository;

public class AssociateService {
	
	@Autowired
	private AssociateRepository repository;
	
	public List<Associate> findAll() {
		return repository.findAll();
	}
	
}
