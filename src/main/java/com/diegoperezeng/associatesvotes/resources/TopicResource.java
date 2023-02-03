package com.diegoperezeng.associatesvotes.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diegoperezeng.associatesvotes.entities.Topic;
import com.diegoperezeng.associatesvotes.services.TopicService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1/topics")
@Api(value = "Topic Management System", description = "Operations pertaining to topics in the Topic Management System")
public class TopicResource {

	@Autowired
	public TopicService topicService;

	// Item: Listar pautas
	@GetMapping
	@ApiOperation(value = "View a list of available topics", response = List.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 204, message = "The list is empty")
	})
	public ResponseEntity<List<Topic>> getAllTopics() {
		List<Topic> topics = topicService.getAllTopics();
		if (topics.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(topics, HttpStatus.OK);
	}

	// Item: Cadastrar uma nova pauta
	@PostMapping("/save")
	@ApiOperation(value = "Add a new topic")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Successfully created topic"),
			@ApiResponse(code = 400, message = "Bad request")
	})
	public ResponseEntity<Topic> saveTopic(@RequestBody Topic topic) {
		try {
			topicService.saveTopic(topic.getTitle(), topic.getDescription(), topic.getOpenStatus());
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

}