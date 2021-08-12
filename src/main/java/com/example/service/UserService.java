package com.example.service;

import java.util.List;

import com.example.entity.Grp;
import com.example.entity.User;

public interface UserService {

	public User registration(User user);

	public User authenticate(String emailId, String password);

	public Grp addGroup(Grp grp);
	
	public List<Grp> listGroup(String emailId);

}
