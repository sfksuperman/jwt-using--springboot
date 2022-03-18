package com.jwt.model;

public class JwtRequest {
	String username;
	String password;
	
	// getter-setter
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	// default-constructor
	public JwtRequest() {
	}
	
	// parameterized-constructor
	public JwtRequest(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	// toString
	@Override
	public String toString() {
		return "JwtRequest [username=" + username + ", password=" + password + "]";
	}

}
