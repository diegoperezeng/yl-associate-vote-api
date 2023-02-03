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

@RestController
@RequestMapping("/api/v1/topics")
public class TopicResource {

	@Autowired
	public TopicService topicService;

	// Item: Listar pautas
	@GetMapping
	public ResponseEntity<List<Topic>> getAllTopics() {
		List<Topic> topics = topicService.getAllTopics();
		if (topics.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(topics, HttpStatus.OK);
	}

	// Item: Cadastrar uma nova pauta
	@PostMapping("/save")
	public ResponseEntity<Topic> saveTopic(@RequestBody Topic topic) {
		try {
			topicService.saveTopic(topic.getTitle(), topic.getDescription(), topic.getOpenStatus());
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

}