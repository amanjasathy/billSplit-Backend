package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.User;
import com.example.service.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	 
	@PostMapping(value = "/registration")
	public User userRegistration(@RequestBody User user) {
		System.out.println(user);
		return userService.registration(user);
		
	}
	@PutMapping(value="/authenticate")
	public User userAuthentication(@RequestParam("emailId") String emailId,@RequestParam("password") String password){
		return userService.authenticate(emailId, password);
	}

}
