package com.subh.msUserServiceApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.subh.msUserServiceApp.entity.User;
import com.subh.msUserServiceApp.repository.UserRepo;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepo repo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// get the username(email) from repo
           User user = repo.findByEmail(email)
        		   .orElseThrow(() -> new UsernameNotFoundException("User not found "+ email));
		
           return org.springframework.security.core.userdetails.User
				.withUsername(email)
				.password(user.getPassword()) //BCrypt Password
				.roles("USER")
				.build();
	}

}
