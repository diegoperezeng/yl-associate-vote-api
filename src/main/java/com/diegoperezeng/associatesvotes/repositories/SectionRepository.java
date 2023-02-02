package com.diegoperezeng.associatesvotes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.diegoperezeng.associatesvotes.entities.Section;


public interface SectionRepository extends JpaRepository<Section, Long> {

}
