package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Grp;
import com.example.entity.JWTRequest;
import com.example.entity.JWTResponse;
import com.example.entity.User;
import com.example.service.CustomUserDetailsService;
import com.example.service.UserService;
import com.example.utility.JWTUtility;

@RestController

public class UserController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Autowired
	private JWTUtility jwtUtility;

	@Autowired
	private UserService userService;

	@GetMapping("/")
	public String root(@RequestParam("name") String name) {
		return "This is Root Page. Hi " + name;
	}

	@PostMapping(value = "/user/registration")
	public User userRegistration(@RequestBody User user) {
		System.out.println(user);
		return userService.registration(user);

	}

	@PutMapping(value = "/authenticate")
	public User userAuthentication(@RequestParam("emailId") String emailId, @RequestParam("password") String password) {
		return userService.authenticate(emailId, password);
	}

	@PostMapping(value = "/user/authenticate")
	public JWTResponse authentication(@RequestBody JWTRequest jwtRequest) throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(jwtRequest.getEmailId(), jwtRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID CREDENTIALS", e);
		}

		final UserDetails userDetails = customUserDetailsService.loadUserByUsername(jwtRequest.getEmailId());

		final String token = jwtUtility.generateToken(userDetails);

		return new JWTResponse(token);
	}
	
	@PostMapping(value = "/user/addGroup")
	public Grp addGrp(@RequestBody Grp grp) {
		return userService.addGroup(grp);
	}

}
