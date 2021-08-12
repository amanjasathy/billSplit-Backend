package com.example.entity;

public class JWTRequest {

	private String emailId;
	private String password;

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public JWTRequest(String emailId, String password) {
		super();
		this.emailId = emailId;
		this.password = password;
	}

}
