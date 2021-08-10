package com.example.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.entity.User;
import com.example.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	
	
//	@Override
//	public User registration(User user) {
//		User usr=userRepository.findByEmailId(user.getEmailId());
//		if(usr==null) {
//			return userRepository.save(user);
//		}
//		return null;
//	}	
	@Override
	public User registration(User user) {
		User usr=userRepository.findByEmailId(user.getEmailId());
		if(usr==null) {
			String password=user.getPassword();
			String encryptedpassword=passwordEncoder.encode(password);
			user.setPassword(encryptedpassword);
			return userRepository.save(user);
		}
		return null;
	}	
	public User authenticate(String emailId,String password) {
		User usr=userRepository.findByEmailId(emailId);
		//System.out.println(usr);
		//System.out.println(emailId);
		if(usr!=null) {
			if(password.equals(usr.getPassword())) {
				//System.out.println(usr);
				return usr;
			}
				
		}
		return null;
	}

}