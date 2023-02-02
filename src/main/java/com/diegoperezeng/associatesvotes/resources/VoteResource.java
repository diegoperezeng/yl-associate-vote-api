package com.diegoperezeng.associatesvotes.resources;

import java.sql.Timestamp;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diegoperezeng.associatesvotes.entities.Vote;

@RestController
@RequestMapping(value = "/votes")
public class VoteResource {
	
	@GetMapping
	public ResponseEntity<Vote> findVote(){
		Timestamp rightNow = new Timestamp(System.currentTimeMillis());
		Vote vote = new Vote(1L, 1L, 1L, Vote.VoteOption.yes, rightNow);
		return ResponseEntity.ok().body(vote);
	}
}
