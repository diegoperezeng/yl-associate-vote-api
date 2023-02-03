package com.diegoperezeng.associatesvotes.resources;

import java.sql.Timestamp;
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

import com.diegoperezeng.associatesvotes.entities.Section;
import com.diegoperezeng.associatesvotes.services.SectionService;
import com.diegoperezeng.associatesvotes.services.TopicResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1/sections")
public class SectionResource {

	@Autowired
	private SectionService sectionService;

	public SectionResource(SectionService sectionService) {
		this.sectionService = sectionService;
	}

	@ApiOperation(value = "Get all sections")
	@GetMapping
	public List<Section> getAllSections() {
		try {
			return sectionService.getAllSections();
		} catch (Exception ex) {
			return null;
		}
	}

	@ApiOperation(value = "Get a section by id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved section"),
			@ApiResponse(code = 404, message = "Section not found")	})
	@GetMapping("/{id}")
	public Section findTopicById(@PathVariable Long id) {
		try {
			return sectionService.findSectionById(id);
		} catch (Exception ex) {
			return null;
		}
	}

	@ApiOperation(value = "Count votes and give the voting result on the topic / Item4: Contar os votos e dar o resultado da votação na pauta")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved result"),
			@ApiResponse(code = 404, message = "Result not found")
	})
	@GetMapping("/result/{topicId}")
	public TopicResult sectionResult(@PathVariable Long topicId) {
		try {
			return sectionService.sectionResult(topicId);
		} catch (Exception ex) {
			return null;
		}
	}

	@ApiOperation(value = "Open a voting session on a topic / Item2: Abrir uma sessão de votação em uma pauta")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Successfully created a new session"),
			@ApiResponse(code = 400, message = "Bad request")
	})
	@PostMapping("/start/{topicId}")
	public ResponseEntity<Section> saveSection(@PathVariable Long topicId, @RequestBody Timestamp startTime, @RequestBody Timestamp endTime, Boolean isOpen){
		try {
			Section savedSession = sectionService.saveSection(topicId, startTime, endTime, true);
			return new ResponseEntity<>(savedSession, HttpStatus.CREATED);
		} catch (Exception ex) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}