package com.subh.msUserServiceApp.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.subh.msUserServiceApp.entity.User;
import com.subh.msUserServiceApp.service.UserServiceImpl;

@RestController
@RequestMapping("/api/v1/users")
public class UserServiceController {
	
	private final UserServiceImpl service;

    public UserServiceController(UserServiceImpl service) {
        this.service = service;
    }
    
	@PostMapping("/register")
	public ResponseEntity<String> regiserNewUser(@RequestBody User  user){

			User  newUser = service.registerUser(user);
			String usercreated = "user registered sucessfully";
		ResponseEntity<String> userentity = new ResponseEntity<String>(usercreated,HttpStatus.OK);
		return userentity;
		
	}
}
