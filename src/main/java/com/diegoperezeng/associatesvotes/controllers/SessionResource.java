package com.diegoperezeng.associatesvotes.controllers;

import java.util.Collections;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diegoperezeng.associatesvotes.controllers.config.SessionPost;
import com.diegoperezeng.associatesvotes.controllers.exceptions.ErrorResponse;
import com.diegoperezeng.associatesvotes.entities.Session;
import com.diegoperezeng.associatesvotes.services.SessionService;
import com.diegoperezeng.associatesvotes.services.config.TopicResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1/sessions")
@Api(value = "Session Management System", description = "Operations related to sessions", tags = {
		"3 - Session Management System" })
public class SessionResource {

	@Autowired
	private SessionService sessionService;

	public SessionResource(SessionService sessionService) {
		this.sessionService = sessionService;
	}

	@ApiOperation(value = "Get all sessions")
	@GetMapping
	public List<Session> getAllSessions() {
		try {
			return sessionService.getAllSessions();
		} catch (Exception ex) {
			return Collections.emptyList();
		}
	}

	@ApiOperation(value = "Get a session by id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved session"),
			@ApiResponse(code = 404, message = "Session not found") })
	@GetMapping("/{id}")
	public Session findTopicById(@PathVariable Long id) {
		try {
			return sessionService.findSessionById(id);
		} catch (Exception ex) {
			return null;
		}
	}

	@ApiOperation(value = "Count votes and give the voting result on the topic / Item4: Contar os votos e dar o resultado da votação na pauta", tags = {
			"Organized - Associate Vote Challenge Endpoints", "3 - Session Management System" })
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully Retrieved Result"),
			@ApiResponse(code = 404, message = "Result not found")
	})
	@GetMapping("/result/{topicId}")
	public TopicResult sessionResult(@PathVariable Long topicId) {
		try {
			return sessionService.sessionResult(topicId);
		} catch (Exception e) {
			return null;
		}
	}

	@ApiOperation(value = "Open a voting session on a topic / Item2: Abrir uma sessão de votação em uma pauta", tags = {
			"Organized - Associate Vote Challenge Endpoints", "3 - Session Management System" })
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Successfully created a new session"),
			@ApiResponse(code = 406, message = "Not Acceptable")
	})
	@PostMapping("/start")
	public ResponseEntity<?> saveSession(
			@RequestBody @ApiParam(value = "Session data", required = true) SessionPost sessionPost)
			throws ConstraintViolationException {
		try {
			sessionService.saveSession(sessionPost.getTopicId(), sessionPost.getStartTime(), sessionPost.getEndTime(),
					sessionPost.getIsOpen());
			return new ResponseEntity<>("Successfully created a new session", HttpStatus.CREATED);
		} catch (Exception e) {
			return ErrorResponse.getResponse(e);
		}
	}
}
