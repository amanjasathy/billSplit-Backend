package com.example.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.entity.Grp;
import com.example.entity.User;
import com.example.exception.SplitBillException;
import com.example.repository.GrpRepository;
import com.example.repository.UserRepository;
import com.example.repository.UsergrpmapRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private GrpRepository grpRepository;

	@Autowired
	private UsergrpmapRepository usergrpmapRepository;

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
	public User registration(User user) throws SplitBillException {
		User usr = userRepository.findByEmailId(user.getEmailId());
		if (usr != null) {
			throw new SplitBillException("User with emailId already exists");
		}
		String password = user.getPassword();
		String encryptedpassword = passwordEncoder.encode(password);
		user.setPassword(encryptedpassword);
		return userRepository.save(user);
	}

	public User authenticate(String emailId, String password) throws SplitBillException {
		User usr = userRepository.findByEmailId(emailId);
		// System.out.println(usr);
		// System.out.println(emailId);
		if (usr != null) {
			if (password.equals(usr.getPassword())) {
				// System.out.println(usr);
				return usr;
			}

		}
		return null;
	}

	public Grp addGroup(Grp grp) throws SplitBillException {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String emailId = userDetails.getUsername();
		grp.setMem1(emailId);
		if (grp.getMem1() != null) {
			User usr = userRepository.findByEmailId(grp.getMem1());
			grp.addUser(usr);
		}
		if (grp.getMem2() != null) {
			User usr = userRepository.findByEmailId(grp.getMem2());
			grp.addUser(usr);
		}
		if (grp.getMem3() != null) {
			User usr = userRepository.findByEmailId(grp.getMem3());
			grp.addUser(usr);
		}
		if (grp.getMem4() != null) {
			User usr = userRepository.findByEmailId(grp.getMem4());
			grp.addUser(usr);
		}
		if (grp.getMem5() != null) {
			User usr = userRepository.findByEmailId(grp.getMem5());
			grp.addUser(usr);
		}
		grp.setTransac("");
		grp.setAmt(0);
		grp.setTransCount(0);
		grp.setDateTimeCreation(LocalDateTime.now());
		grp.setGrpType("DIVIDE");

		return grpRepository.save(grp);

	}

	public List<Grp> listGroup(String emailId) throws SplitBillException {
		User usr = userRepository.findByEmailId(emailId);
		List<Grp> lGrp = null;
		if (usr != null) {
			List<Long> lGrpId = usergrpmapRepository.findGidByUid(usr.getUserId());
			lGrp = grpRepository.findAllById(lGrpId);
		} else
			throw new SplitBillException("Invalid emailId");
		return lGrp;
	}

	public String addTransaction(Long grpId, String transaction, Integer amt) throws SplitBillException {
		Optional<Grp> optional = grpRepository.findById(grpId);
		Grp grp = optional.orElseThrow(() -> new SplitBillException("Invalid groupId"));
		grp.setTransCount(grp.getTransCount() + 1);
		grp.setTransac(grp.getTransac() + grp.getTransCount() + "." + transaction + " ");
		grp.setAmt(grp.getAmt() + amt);
		return "TRANSACTION ADDED";
	}
}
