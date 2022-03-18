package com.jwt.model;

public class JwtResponse {
	String token;

	// getter-setter
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	// default-constructor
	public JwtResponse() {
	}

	// parameterized-constructor
	public JwtResponse(String token) {
		super();
		this.token = token;
	}

}
