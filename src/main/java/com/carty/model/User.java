package com.carty.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

import com.carty.data.Account;
import com.carty.data.Address;
import com.carty.data.HealthPolicy;
import com.carty.data.VehiclePolicy;
import com.carty.model.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
	protected Address address;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name="user_roles", joinColumns= {@JoinColumn(name="user_id", referencedColumnName="id")}, 
	                       inverseJoinColumns= {@JoinColumn(name="role_id", referencedColumnName="id")})
	protected List<Role> roles;

	@OneToOne
	@JoinColumn(name="hpolicy_id")
	protected HealthPolicy hpolicy;
	
	//joinTable mapping using foreign key in related table
	@OneToMany(cascade=CascadeType.ALL)  
	@JoinColumn(name="user_id")
	protected List<VehiclePolicy> vpolicies = new ArrayList<>();
		
	
	//Foreign Key in related table rather than join table
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="user_id")
	protected List<Account> accounts = new ArrayList<>();
	
	@Basic //basic date format
	protected Date dob;	//dob = java.sql.Date.valueOf("2017-11-15") or new java.sql.Date(new java.util.Date().getTime());
	
	public User(){

	}

	public User(String fname, String lname, String email, String password, Address address, Date dob) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.email = email;
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
			HealthPolicy hpolicy, List<VehiclePolicy> vpolicies, List<Account> accounts, Date dob) {
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
		this.accounts = accounts;
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
	
	public void addRole(Role role) {
		if (this.roles == null) {
			this.roles = new ArrayList<>();
		}
		this.roles.add(role);
	}
	
	public void addRoles(Set<Role> roles) {
		
		if (this.roles == null) {
			this.roles = new ArrayList<>();
		}
		
		this.roles.addAll(roles);
	}


	public HealthPolicy getHPolicy() {
		return hpolicy;
	}

	public void setHPolicy(HealthPolicy policy) {
		this.hpolicy = policy;
	}

	public List<VehiclePolicy> getVPolicies() {
		return vpolicies;
	}

	public void setVPolicies(List<VehiclePolicy> policies) {
		this.vpolicies = policies;
	}
	
 public VehiclePolicy addHPolicy(VehiclePolicy policy) {
		
		if (vpolicies == null) {
			vpolicies = new ArrayList<>();
		}
		
		vpolicies.add(policy);		
	
		return vpolicies.get(vpolicies.size()-1);
		
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dob == null) ? 0 : dob.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((fname == null) ? 0 : fname.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((lname == null) ? 0 : lname.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		User other = (User) obj;
		if (this.id != other.id)
			return false;
		else 
			return true;
	}
	

}
