package com.carty.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class StoredToken {

	@Id
	private long Id;
	@Column
	private String token;
	
	public StoredToken() {
		
	}
	
	public StoredToken(long id, String token) {
		super();
		Id = id;
		this.token = token;
	}

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	
}
