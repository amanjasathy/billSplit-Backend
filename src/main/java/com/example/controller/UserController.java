package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.example.exception.SplitBillException;
import com.example.service.CustomUserDetailsService;
import com.example.service.UserService;
import com.example.utility.JWTUtility;

@RestController
//@RequestMapping(value = "/")
@CrossOrigin(origins = "*")
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
	public String root(@RequestParam("name") String name) throws SplitBillException {
		return "This is Root Page. Hi " + name;
	}

	@PostMapping(value = "/user/registration")
	public User userRegistration(@RequestBody User user) throws SplitBillException {
		System.out.println(user);
		return userService.registration(user);

	}

	@PutMapping(value = "/authenticate")
	public User userAuthentication(@RequestParam("emailId") String emailId, @RequestParam("password") String password)
			throws SplitBillException {
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
	public Grp addGrp(@RequestBody Grp grp) throws SplitBillException {
		return userService.addGroup(grp);
	}

	@GetMapping("/user/groups1")
	public List<Grp> listGroups1(@RequestParam("emailId") String emailId) throws SplitBillException {
		return userService.listGroup(emailId);
	}

	@GetMapping("/user/groups")
	public List<Grp> listGroups() throws SplitBillException {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String emailId = userDetails.getUsername();
		return userService.listGroup(emailId);
	}

	@PutMapping(value = "user/addTransaction")
	public String addTransaction(@RequestParam("grpId") Long grpId, @RequestParam("transaction") String transaction,
			@RequestParam("amt") Integer amt) throws SplitBillException {
		return userService.addTransaction(grpId, transaction, amt);
	}
}
