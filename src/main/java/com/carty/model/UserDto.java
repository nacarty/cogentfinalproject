package com.carty.model;

import java.sql.Date;

import com.carty.data.Address;

public class UserDto {

	public String fname;
	public String lname;
	public String email;
	public String password;
	public Address address;
	public Date dob;
	
	UserDto(){
		
	}

	public UserDto(String fname, String lname, String email, String password, Address address) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.password = password;
		this.address = address;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "UserDto [fname=" + fname + ", lname=" + lname + ", email=" + email + ", password=" + password
				+ ", address=" + address.toString() + "]";
	}
	
	
	}