package com.example.service;

import java.util.List;

import com.example.entity.Grp;
import com.example.entity.User;
import com.example.exception.SplitBillException;

public interface UserService {

	public User registration(User user) throws SplitBillException;

	public User authenticate(String emailId, String password) throws SplitBillException;

	public Grp addGroup(Grp grp) throws SplitBillException;

	public List<Grp> listGroup(String emailId) throws SplitBillException;

	public String addTransaction(Long grpId, String transaction, Integer amt) throws SplitBillException;

}
