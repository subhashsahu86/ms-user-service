package com.subh.msUserServiceApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.subh.msUserServiceApp.config.SecurityConfig;
import com.subh.msUserServiceApp.entity.User;
import com.subh.msUserServiceApp.repository.UserRepo;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserRepo userRepo;
	

	private final BCryptPasswordEncoder passwordEncoder;
	
	public UserServiceImpl(BCryptPasswordEncoder passwordEncoder) {
	
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public User registerUser(User user) {
		
		//Hash the password
		String hashedPassword = passwordEncoder.encode(user.getPassword());
		
		user.setPassword(hashedPassword);
		
		return userRepo.save(user);
		
	}

	@Override
	public String loginUser(User user) {
	
		return null;
	}
	
}
