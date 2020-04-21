//package com.carty.data;
//
//import java.sql.Date;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//import javax.persistence.Basic;
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.JoinTable;
//import javax.persistence.ManyToMany;
//import javax.persistence.OneToMany;
//import javax.persistence.OneToOne;
//
//import com.carty.model.Role;
//import com.fasterxml.jackson.annotation.JsonIgnore;
//
////@Entity
////@Inheritance(strategy = InheritanceType.JOINED)
//public class Y_Backup_User {
//	
//	@Id
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
//	protected long id;
//	
//	@Column(nullable = false, length = 45)
//	protected String fname;
//	
//	@Column(nullable = false, length = 45)
//	protected String lname;
//	
//	@Column(unique = true, nullable = false, length = 45)
//	private String email;
//	
//	@Column(nullable = false)
//	@JsonIgnore
//	private String password;
//	
//	@OneToOne
//	@JoinColumn(name="user_id")//not necessary as this will automatically be done by Hibernate
//	protected Address address;
//	
//	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//	@JoinTable(name="user_roles", joinColumns= {@JoinColumn(name="user_id", referencedColumnName="id")}, 
//	                       inverseJoinColumns= {@JoinColumn(name="role_id", referencedColumnName="id")})
//	protected Set<Role> roles;
//
//	
//	@OneToMany(cascade=CascadeType.ALL)  //This implementation uses a join table rather than simply using a foreign key in the second table
//	@JoinTable(name="user_policy", joinColumns={@JoinColumn(name="user_id", referencedColumnName="id")}
//    , inverseJoinColumns={@JoinColumn(name="policy_id", referencedColumnName="id")})
//	protected List<Policy> policies;
//	
//	@OneToMany(cascade=CascadeType.ALL)
//	@JoinColumn(name="user_id")
//	protected List<Account> accounts;
//	
//	@Basic //basic date format
//	protected Date dob;	//dob = java.sql.Date.valueOf("2017-11-15") or new java.sql.Date(new java.util.Date().getTime());
//	
//	public Y_Backup_User(){
//		
//	}
//
//	public Y_Backup_User(String fname, String lname, String email, String password, Address address, Set<Role> roles,
//			List<Policy> policies, List<Account> accounts, Date dob) {
//		super();
//		this.fname = fname;
//		this.lname = lname;
//		this.email = email;
//		this.password = password;
//		this.address = address;
//		
//		if (roles == null) {
//			Set<Role> ar = new HashSet<>();
//			Role r = new Role(1);
//			ar.add(r);
//			this.roles = ar;
//		}
//		else {
//			this.roles = roles;
//		}
//		
//		this.policies = policies;
//		this.accounts = accounts;
//		this.dob = dob;
//	}
//
//	public long getId() {
//		return id;
//	}
//
//	public void setId(long id) {
//		this.id = id;
//	}
//
//	public String getFname() {
//		return fname;
//	}
//
//	public void setFname(String fname) {
//		this.fname = fname;
//	}
//
//	public String getLname() {
//		return lname;
//	}
//
//	public void setLname(String lname) {
//		this.lname = lname;
//	}
//
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//	public String getPassword() {
//		return password;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}
//
//	public Address getAddress() {
//		return address;
//	}
//
//	public void setAddress(Address address) {
//		this.address = address;
//	}
//
//	public Set<Role> getRoles() {
//		return roles;
//	}
//
//	public void setRoles(Set<Role> roles) {
//		this.roles = roles;
//	}
//
//	public List<Policy> getPolicies() {
//		return policies;
//	}
//
//	public void setPolicies(List<Policy> policies) {
//		this.policies = policies;
//	}
//
//	public List<Account> getAccounts() {
//		return accounts;
//	}
//
//	public void setAccounts(List<Account> accounts) {
//		this.accounts = accounts;
//	}
//
//	public Date getDob() {
//		return dob;
//	}
//
//	public void setDob(Date dob) {
//		this.dob = dob;
//	}
//	
//}
