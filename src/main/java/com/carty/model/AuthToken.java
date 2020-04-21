package com.carty.model;

public class AuthToken {
   
	private String token;
	
	public AuthToken() {}
	
	public AuthToken(String s ) {
		super();
		this.token = s;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}	
	
}
