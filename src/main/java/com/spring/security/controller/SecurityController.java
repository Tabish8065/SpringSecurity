package com.spring.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.security.classes.JwtUtils;
import com.spring.security.model.JwtRequest;

@RestController
public class SecurityController {
	
	@GetMapping("/hello")
	public ResponseEntity<String> getMapping(){
		System.out.println("HelloMapping");
		return ResponseEntity.ok("Hello");
	}
	
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private JwtUtils jwtUtils;

	@PostMapping("/authenticated")
	public ResponseEntity<String> authenticate(@RequestBody JwtRequest request){
		System.out.println(request.getUsername()+" "+request.getPassword());
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		final UserDetails user = userDetailsService.loadUserByUsername(request.getUsername());
		System.out.println("Line 33: " + user.toString());
		if(user != null) {
			String token = jwtUtils.generateToken(user);
			System.out.println(token);
			return ResponseEntity.ok(token);
		}
		
		return ResponseEntity.status(400).body("Some thing happen");
	}

}