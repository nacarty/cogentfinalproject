package com.carty.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

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
	
	protected long managerId; //I have the option of making this an Employee field but it will lead to having to double map Branch in the Employee entity  
	
	@OneToMany(cascade=CascadeType.ALL)  //for join with the Employee Table/Entity on Foreign Key (not join table)
	@JoinColumn(name="branch_id")
	protected List<Employee> employees = new ArrayList<>();
	
	@OneToOne
	@JoinColumn(name="address_id")
	protected Address address;
	
	
	Branch(){
		
	}
	
	public Branch(Employee manager, Address address) {//create branch
		this.managerId = manager.getId();
		this.address = address;
	}

	public Branch(int managerId, ArrayList<Employee> employees, Address address) { //create branch
		this.managerId = managerId;
		this.employees = employees;
		this.address = address;
	}

	public void setManager(Employee manager) {
		this.managerId = manager.getId();
	}
	
	public void setManager(int managerId) {
		this.managerId = managerId;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getManager() {
		return managerId;
	}	

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(ArrayList<Employee> employees) {
		this.employees = employees;
	}
	
	public void addEmployee(Employee employee) {
		this.employees.add(employee);
	}
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public long getManagerId() {
		return managerId;
	}

	public void setManagerId(long managerId) {
		this.managerId = managerId;
	}

	@Override
	public String toString() {
		return "Branch [id=" + id + ", managerId=" + managerId + ", employees=" + employees + ", address=" + address.toString()
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + (int) (managerId ^ (managerId >>> 32));
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
		if (this.id != other.id)
			return false;
		else 
			return true;
	}	
	
	
}

