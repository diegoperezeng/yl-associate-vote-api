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
import com.diegoperezeng.associatesvotes.services.AssociateService;

@RestController
@RequestMapping("/api/associates")
public class AssociateResource {

	@Autowired
	private AssociateService associateService;
	
	public AssociateResource(AssociateService associateService) {
		this.associateService = associateService;
	}

	@GetMapping
	public List<Associate> getAllAssociates() {
		return associateService.getAllAssociates();
	}

	@GetMapping("/{id}")
	public Associate getAssociateById(@PathVariable Long id) {
		return associateService.findAssociateById(id);
	}

	@PostMapping
	public ResponseEntity<Topic> saveAssociate(@RequestBody Associate associate) throws ConstraintViolationException {
		try {
			associateService.saveAssociate(associate.getName(), associate.getEmail());
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}

}