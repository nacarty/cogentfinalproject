package com.carty.model;

import java.util.Set;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

//@Entity
public class Z_Backup_User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long Id;
	@Column
	private String fname;
	@Column
	private String lname;
	@Column
	private String email;
	@Column
	@JsonIgnore
	private String password;
	@Column
	private String address;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name="USER_ROLES", joinColumns= {@JoinColumn(name="USER_ID")}, inverseJoinColumns= {@JoinColumn(name="ROLE_ID")})
	private Set<Role> roles;

	
	public Z_Backup_User() {

	}
	
	public Z_Backup_User(long id, String fname, String lname, String email, String password, String address, Set<Role> roles) {
		super();
		Id = id;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.password = password;
		this.address = address;
		this.roles = roles;
	}

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

}

	
