package com.diegoperezeng.associatesvotes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.diegoperezeng.associatesvotes.entities.Session;


public interface SessionRepository extends JpaRepository<Session, Long> {

    public Integer findVoteCountYesByTopicId(Long topicId);
    public Integer findVoteCountYesById(Long id);
}
