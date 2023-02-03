package com.diegoperezeng.associatesvotes.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diegoperezeng.associatesvotes.entities.Topic;
import com.diegoperezeng.associatesvotes.entities.Vote;
import com.diegoperezeng.associatesvotes.services.VoteService;

@RestController
@RequestMapping("/api/v1/votes")
public class VoteResource {

	@Autowired
	private VoteService voteService;

	// Returns all votes
	@GetMapping
	public List<Vote> getAllVotes() {
		return voteService.getAllVotes();
	}

	// Returns a vote by id
	@GetMapping("/{id}")
	public Vote findVoteById(@PathVariable Long id) {
		return voteService.findVoteById(id);
	}

	// Creates a new vote for an associate in a particular section
	@PostMapping("/save")
	public ResponseEntity<Topic> saveVote(@RequestBody Vote vote) {
		try {
			voteService.saveVote(vote.getSectionId(), vote.getAssociateId(), vote.getVoteChoice());
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}