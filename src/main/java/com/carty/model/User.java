package com.carty.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.carty.data.Address;
import com.carty.data.HealthPolicy;
import com.carty.data.Job;
import com.carty.data.VehiclePolicy;
import com.carty.model.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements Serializable{
	

	@Transient
	private static final long serialVersionUID = 7171261198830003436L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected long id;
	
	@Column(nullable = false, length = 45)
	protected String fname;
	
	@Column(nullable = false, length = 45)
	protected String lname;
	
	@Column(unique = true, nullable = false, length = 45)
	protected String email;
	
	@Column(nullable = false)
	@JsonIgnore
	protected String password;
	
	@OneToOne
	@JoinColumn(name="address_id")//not necessary as this will automatically be done by Hibernate
	public Address address;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name="user_roles", joinColumns= {@JoinColumn(name="user_id", referencedColumnName="id")}, 
	                       inverseJoinColumns= {@JoinColumn(name="role_id", referencedColumnName="id")})
	public List<Role> roles;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="hpolicy_id")
	protected HealthPolicy hpolicy;
	
	//joinTable mapping using foreign key in related table
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name="user_vehpolicy", joinColumns= {@JoinColumn(name="user_id", referencedColumnName="id")}, 
	                       inverseJoinColumns= {@JoinColumn(name="policy_id", referencedColumnName="id")})
	protected List<VehiclePolicy> vpolicies = new ArrayList<>();
		
	@Basic //basic date format
	protected Date dob;	//dob = java.sql.Date.valueOf("2017-11-15") or new java.sql.Date(new java.util.Date().getTime());
	
	protected long agentId;
	
	@Column(unique = true, nullable = false, length = 10)
	protected String SSN;
	
	@OneToOne
	@JoinColumn(name="job_id")
	@JsonManagedReference  //we can also simply eliminate the getUser method in the Job entity
	protected Job job;
	
	public User(){

	}
	
	public User(String fn, String ln) {
		this.fname = fn;
		this.fname = ln;
	}

	public User(String fname, String lname, String email, String SSN, String password, Address address, Date dob) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.SSN = SSN;
		this.password = password;
		this.address = address;
		this.dob = dob;
	
		
		if (this.roles == null) {
			this.roles = new ArrayList<>();
		}
			Role role = new Role(1);
			this.roles.add(role);
	}

	public User(String fname, String lname, String email, String password, Address address, List<Role> roles,
			HealthPolicy hpolicy, List<VehiclePolicy> vpolicies, Date dob) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.password = password;
		this.address = address;
		
		if (this.roles == null) {
			this.roles = new ArrayList<>();
		}
			Role r = new Role(1);
			this.roles.add(r);
			
		this.vpolicies = vpolicies;
		this.hpolicy = hpolicy;
		this.dob = dob;
	  }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public HealthPolicy getHpolicy() {
		return hpolicy;
	}

	public void setHpolicy(HealthPolicy hp) {
		this.hpolicy = hp;
	}

	public List<VehiclePolicy> getVpolicies() {
		return vpolicies;
	}

	public void setVpolicies(List<VehiclePolicy> vpolicies) {
		this.vpolicies = vpolicies;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getSSN() {
		return SSN;
	}

	public void setSSN(String sSN) {
		SSN = sSN;
	}

	public long getAgentId() {
		return agentId;
	}

	public void setAgentId(long agentId) {
		this.agentId = agentId;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

}
