package com.carty.data;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.carty.model.User;

@Entity
public class Branch implements Serializable{

	/**
	 * 
	 */
	@Transient
	private static final long serialVersionUID = -6742786944811066785L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	protected long managerId; //I have the option of making this a User field but it will lead to having to double map Branch in the User entity  
	
	@OneToMany(cascade=CascadeType.ALL)
    @JoinTable(name="branch_user", joinColumns={@JoinColumn(name="branch_id", referencedColumnName="id")}
    , inverseJoinColumns={@JoinColumn(name="user_id", referencedColumnName="id")})
	protected List<User> users;
	
	@OneToOne
	@JoinColumn(name="address_id")
	protected Address address;
	
	protected Date established = new Date(System.currentTimeMillis());  //or new java.util.Date().getTime()
	
	
	public Date getEstablished() {
		return established;
	}


	public void setEstablished(Date established) {
		this.established = established;
	}


	Branch(){
		
	}
	

	public Branch(Address address) {//create branch
		this.managerId = 0;
		this.address = address;
	}
	
	public Branch(long managerId, Address address) {//create branch
		this.managerId = managerId;
		this.address = address;
	}

	public Branch(int managerId, List<User> users, Address address) { //create branch
		this.managerId = managerId;
		this.users = users;
		this.address = address;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}

	public long getManagerId() {
		return managerId;
	}

	public void setManagerId(long managerId) {
		this.managerId = managerId;
	}


	public List<User> getUsers() {
		return users;
	}


	public void setUsers(List<User> users) {
		this.users = users;
	}


	public Address getAddress() {
		return address;
	}


	public void setAddress(Address address) {
		this.address = address;
	}


	@Override
	public String toString() {
		return "Branch [id=" + id + ", managerId=" + managerId + ", users=" + users + ", address=" + address
				+ ", established=" + established + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((established == null) ? 0 : established.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
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
		Branch other = (Branch) obj;
		if (established == null) {
			if (other.established != null)
				return false;
		} else if (!established.equals(other.established))
			return false;
		if (id != other.id)
			return false;
		return true;
	}

	}

