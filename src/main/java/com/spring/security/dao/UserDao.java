package com.spring.security.dao;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
	private final static List<UserDetails> APPLICATION_USERS = Arrays.asList(
			new User(
						"Tabish@8065",
						"Tabish",
						Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"))
					)
		);
	
	public UserDetails findUserByUsername(String username) {
		return APPLICATION_USERS.stream().filter(e -> e.getUsername() == username).findFirst().orElseThrow(() -> new UsernameNotFoundException(username));
	}
}