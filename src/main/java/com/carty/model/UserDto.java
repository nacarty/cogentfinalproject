package com.carty.model;

import java.sql.Date;
import java.util.List;

import com.carty.data.Address;

public class UserDto {

	public String fname;
	public String lname;
	public String email;
	public String password;
	public String SSN;
	public Address address;
	public List<Role> roles;
	public Date dob;
	public long agentId;
	
	UserDto(){
		
	}

	public UserDto(String fname, String lname, String email, String password, String SSN, Address address, List<Role> roles) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.password = password;
		this.SSN = SSN;
		this.address = address;
		this.roles = roles;
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

	
	public String getSSN() {
		return SSN;
	}

	public void setSSN(String sSN) {
		SSN = sSN;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public long getAgentId() {
		return agentId;
	}

	public void setAgentId(long agentId) {
		this.agentId = agentId;
	}

	@Override
	public String toString() {
		return "UserDto [fname=" + fname + ", lname=" + lname + ", email=" + email + ", password=" + password + ", SSN="
				+ SSN + ", address=" + address + ", roles=" + roles + ", dob=" + dob + ", agentId=" + agentId + "]";
	}

	
	
	}
