package com.diegoperezeng.associatesvotes.resources;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diegoperezeng.associatesvotes.entities.Section;
import com.diegoperezeng.associatesvotes.services.SectionService;
import com.diegoperezeng.associatesvotes.services.TopicResult;

@RestController
@RequestMapping("/sections")
public class SectionResource{

	@Autowired
	private SectionService sectionService;

	public SectionResource(SectionService sectionService) {
		this.sectionService = sectionService;
	}

	// Item: Obter todas as sessões de votação.
	@GetMapping
	public List<Section> getAllSections() {
		try {
			return sectionService.getAllSections();
			
		} catch (Exception ex) {
			return null;
		}
	}	

	// Item: Obter uma sessão de votação por id
	//
	@GetMapping("/{id}")
	public Section findTopicById(@PathVariable Long id) {
		try {
			return sectionService.findSectionById(id);
		} catch (Exception ex) {
			return null;
		}
	}

	// Item: Contabilizar os votos e dar o resultado da votação na pauta.
	@GetMapping("/{topicId}/result")
	public TopicResult sectionResult(@PathVariable Long topicId) {
		try {
			return sectionService.sectionResult(topicId);
		} catch (Exception ex) {
			return null;
		}
	}

	// Item: Abrir uma sessão de votação em uma pauta
	@PostMapping("/{topicId}/start")
	public ResponseEntity<Section> saveSection(@PathVariable Long topicId, @RequestBody Timestamp startTime, @RequestBody Timestamp endTime, Boolean isOpen){
		try {
			Section savedSession = sectionService.saveSection(topicId, startTime, endTime, true);
			return new ResponseEntity<>(savedSession, HttpStatus.CREATED);
		} catch (Exception ex) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}