package com.spring.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.security.classes.JwtUtils;
import com.spring.security.model.JwtRequest;

import lombok.RequiredArgsConstructor;

@RestController
public class AuthenticationController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private JwtUtils jwtUtils;

	@PostMapping("/authenticated")
	public ResponseEntity<String> authenticate(@RequestBody JwtRequest request){
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		
		final UserDetails user = userDetailsService.loadUserByUsername(request.getUsername());
		
		if(user != null) {
			return ResponseEntity.ok(jwtUtils.generateToken(user));
		}
		
		return ResponseEntity.status(400).body("Some thing happen");
	}
}
