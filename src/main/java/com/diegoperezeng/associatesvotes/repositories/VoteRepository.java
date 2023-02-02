package com.diegoperezeng.associatesvotes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.diegoperezeng.associatesvotes.entities.Vote;

public interface VoteRepository extends JpaRepository<Vote, Long> {

}