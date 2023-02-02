package com.diegoperezeng.associatesvotes.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.diegoperezeng.associatesvotes.entities.Associate;
import com.diegoperezeng.associatesvotes.repositories.AssociateRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
	
	@Autowired
	private AssociateRepository associateRepository;

	@Override
	public void run(String... args) throws Exception {
		
		Associate a1 = new Associate(null, "ObiWan Kenobi", "obiwan@resistence.com", null);
		Associate a2 = new Associate(null, "QuiGon Jin", "quigon@resistence.com", null);
		
		associateRepository.saveAll(Arrays.asList(a1, a2));
		
	}
	
	
	

}
