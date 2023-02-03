package com.diegoperezeng.associatesvotes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.diegoperezeng.associatesvotes.entities.Associate;


public interface AssociateRepository extends JpaRepository<Associate, Long>{	
	

}
