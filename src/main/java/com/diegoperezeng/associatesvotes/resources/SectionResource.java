package com.diegoperezeng.associatesvotes.resources;

import java.sql.Timestamp;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diegoperezeng.associatesvotes.entities.Section;
import com.diegoperezeng.associatesvotes.entities.Topic;
import com.diegoperezeng.associatesvotes.services.SectionService;
import com.diegoperezeng.associatesvotes.services.TopicResult;

@RestController
@RequestMapping("/sections")
public class SectionResource {

	@Autowired
	private SectionService sectionService;

	public SectionResource(SectionService sectionService) {
		this.sectionService = sectionService;
	}

	// Item: Contabilizar os votos e dar o resultado da votação na pauta
	//
	@GetMapping("/{topicId}/result")
	public TopicResult sectionResult(@PathVariable Long topicId) {
		return sectionService.sectionResult(topicId);
	}

	// Item: Abrir uma sessão de votação em uma pauta
	//
	@PostMapping("/{topicId}/start")
	public ResponseEntity<Topic> saveTopic(@PathVariable Long topicId, @RequestBody Timestamp startTime,
			@RequestBody Timestamp endTime) throws ConstraintViolationException {
		try {			
			sectionService.saveTopic(topicId, startTime, endTime);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	// Item: Obter todas as sessões de votação
	//
	@GetMapping("/all")
	public List<Section> getAllTopics() {
		return sectionService.getAllTopics();
	}
	
	// Item: Obter uma sessão de votação por id
	//
	@GetMapping("/{id}")
	public Section findTopicById(@PathVariable Long id) {
		return sectionService.findTopicById(id);
	}
}