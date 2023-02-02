package com.diegoperezeng.associatesvotes.resources;

import java.sql.Timestamp;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diegoperezeng.associatesvotes.entities.Topic;

@RestController
@RequestMapping(value = "/topic")
public class TopicResource {
	
	@GetMapping
	public ResponseEntity<Topic> findTopic(){
		Timestamp rightNow = new Timestamp(System.currentTimeMillis());
		Topic topic = new Topic(1L, "title", "description", true, rightNow);
		return ResponseEntity.ok().body(topic);
	}
}
