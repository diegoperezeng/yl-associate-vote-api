package com.diegoperezeng.associatesvotes.resources;

import java.sql.Timestamp;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diegoperezeng.associatesvotes.entities.Section;

@RestController
@RequestMapping(value = "/section")
public class SectionResource {

	@GetMapping
	public ResponseEntity<Section> findSection(){
		Timestamp rightNow = new Timestamp(System.currentTimeMillis());
		Section section = new Section(1L, 2L, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis() + 3600), true, 10, 5, rightNow);
		return ResponseEntity.ok().body(section);
	}
}
