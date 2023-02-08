package com.diegoperezeng.associatesvotes.resources.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ErrorResponse {
	public static ResponseEntity<?> getResponse(Exception e){
		if(e.getCause().getMessage().contains("could not execute statement")) {
			return new ResponseEntity<>(e.getCause().getCause().getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}else {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
