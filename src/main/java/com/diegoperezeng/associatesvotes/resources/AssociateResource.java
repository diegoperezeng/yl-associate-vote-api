package com.diegoperezeng.associatesvotes.resources;

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

import com.diegoperezeng.associatesvotes.entities.Associate;
import com.diegoperezeng.associatesvotes.entities.Topic;
import com.diegoperezeng.associatesvotes.resources.exceptions.ErrorResponse;
import com.diegoperezeng.associatesvotes.services.AssociateService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1/associates")
@Api(value = "Associate Resource", description = "Operations related to Associates")
public class AssociateResource {

	@Autowired
	private AssociateService associateService;
	
	public AssociateResource(AssociateService associateService) {
		this.associateService = associateService;
	}

	@GetMapping
	@ApiOperation(value = "Get all associates", notes = "Retrieve a list of all associates", response = Associate.class, responseContainer = "List")
	public List<Associate> getAllAssociates() {
		return associateService.getAllAssociates();
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Get associate by id", notes = "Retrieve a associate by providing its id", response = Associate.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved associate"),
			@ApiResponse(code = 404, message = "The associate with given id is not found")
	})
	public Associate getAssociateById(@PathVariable Long id) {
		return associateService.findAssociateById(id);
	}

	@PostMapping("/save")
	@ApiOperation(value = "Save associate", notes = "Save an associate by providing its name and email")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Successfully created associate"),
			@ApiResponse(code = 406, message = "Not Acceptable")
	})
	public ResponseEntity<?> saveAssociate(@RequestBody @ApiParam(value = "Associate data", required = true) Associate associate) throws ConstraintViolationException {
		try {
			associateService.saveAssociate(associate.getName(), associate.getCpf(), associate.getEmail());
			return new ResponseEntity<>("Associate Created Succesfully",HttpStatus.CREATED);
		} catch (Exception e) {
			return ErrorResponse.getResponse(e);
		}
	}
}