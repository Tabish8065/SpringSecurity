package com.spring.security.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {
	
	@GetMapping("/hello")
	public ResponseEntity<String> getMapping(){
		return ResponseEntity.ok("Hello");
	}

}
