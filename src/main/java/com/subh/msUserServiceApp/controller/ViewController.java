package com.subh.msUserServiceApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ViewController {

	@GetMapping("/home")
	public String home() {
		return "home"; //redirect to home page
	} 
	
	@GetMapping("/register")
	public String register(HttpServletRequest request) {
		//if user opens /register directly then it redirect to /home
		String referer = request.getHeader("referer");
		if(referer == null) {
			return "redirect:/home";
		}
		
		return "register"; // redirect to register page
	}
	
	@GetMapping("/login")
	public String login(HttpServletRequest request) {
		//if user opens /login directly then it redirect to /home
		String referer = request.getHeader("referer");
		
		if(referer == null) {
			return "redirect:/home";
			
		}
		return "login"; // redirect to login page
	}
	
	
}
