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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1/votes")
public class VoteResource {

	@Autowired
	private VoteService voteService;

	@ApiOperation(value = "Get all votes")
	@GetMapping
	public List<Vote> getAllVotes() {
		return voteService.getAllVotes();
	}

	@ApiOperation(value = "Get vote by id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful"),
			@ApiResponse(code = 404, message = "Vote not found"),})
	@GetMapping("/{id}")
	public Vote findVoteById(@ApiParam(value = "Id of the vote", required = true) @PathVariable Long id) {
		return voteService.findVoteById(id);
	}

	@ApiOperation(value = "Create a new vote for an associate in a particular section")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Successful"),
			@ApiResponse(code = 400, message = "Bad request")
	})
	@PostMapping("/save")
	public ResponseEntity<Topic> saveVote(@ApiParam(value = "Vote details", required = true) @RequestBody Vote vote) {
		try {
			voteService.saveVote(vote.getSectionId(), vote.getAssociateId(), vote.getVoteChoice());
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}