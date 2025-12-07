package com.subh.msUserServiceApp.service;

import com.subh.msUserServiceApp.entity.User;

public interface IUserService {
	
   public User registerUser(User user);
   
   public String loginUser(User user);

}
