package com.example.service;

import com.example.entity.User;

public interface UserService {

	public User registration(User user);
	public User authenticate(String emailId,String password);

}
