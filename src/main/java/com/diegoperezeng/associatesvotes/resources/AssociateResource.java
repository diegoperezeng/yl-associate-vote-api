package com.diegoperezeng.associatesvotes.resources;

import java.sql.Timestamp;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diegoperezeng.associatesvotes.entities.Associate;

@RestController
@RequestMapping(value = "/associates/{teste}")
public class AssociateResource {
	
	@GetMapping
	public ResponseEntity<Associate> findAssociate(@PathVariable String teste){
		Timestamp rightNow = new Timestamp(System.currentTimeMillis());
		Associate associate = new Associate(1L,teste,"fulaninho@pessoa.com",rightNow);
		return ResponseEntity.ok().body(associate);
	}
}
