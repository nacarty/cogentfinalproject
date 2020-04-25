//package com.carty.data;
//
//import java.sql.Date;
//import java.util.ArrayList;
//import java.util.List;
//
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.ManyToOne;
//import javax.persistence.PrimaryKeyJoinColumn;
//import javax.persistence.Transient;
//
//import com.carty.model.Role;
//import com.carty.model.User;
//
//@Entity
//@PrimaryKeyJoinColumn(name = "user_id")  //for its relationship with parent table User
//public class Employee extends User{
//
//	/**
//	 * 
//	 */
//	@Transient
//	private static final long serialVersionUID = 3815813386408728312L;
//
//	@Column(unique = true, nullable = false, length = 10)
//	protected String SSN;
//	
//	@ManyToOne
//	private Branch branch;  //wiring completed 
//	
//	@ManyToOne
//	private Job job;  //wiring completed
//	
//	public Employee(){
//
//	}
//
//	public Employee(String fname, String lname, String email, String password, Address address, List<Role> roles,
//		   HealthPolicy hpolicy, List<VehiclePolicy> vpolicies, List<Account> accounts, Date dob, Branch branch, Job job) {
//		super(fname, lname, email, password, address, roles, hpolicy, vpolicies, dob);
//	
//		this.branch = branch;
//		this.job = job;		
//		
//		Role r = new Role(roleId(job));
//		this.roles.add(r);
//	}
//	
//		public Employee(User user, Branch branch, Job job) {
//		
//		this.setFname(user.getFname());
//		this.setLname(user.getLname());
//		this.setEmail(user.getEmail());
//		this.setPassword(user.getPassword());
//		this.setAddress(user.getAddress());
//		this.setRoles(user.getRoles());
//		this.setDob(user.getDob());
//		this.branch = branch;
//		
//		this.job = job;
//		
//		Role r = new Role(roleId(job));
//		if (this.roles == null)
//			this.roles = new ArrayList<>();
//		this.roles.add(r);	
//		
//		this.hpolicy = user.getHPolicy();
//		
//		if (this.vpolicies == null)
//			this.vpolicies = new ArrayList<>();
//		
//		this.vpolicies = user.getVPolicies();
//		
//	}
//			
//		public Branch getBranch() {
//			return branch;
//		}
//
//		public void setBranch(Branch branch) {
//			this.branch = branch;
//		}
//
//		public Job getJob() {
//			return job;
//		}
//
//		public void setJob(Job job) {
//			this.job = job;
//		}
//
//		private int roleId(Job j) {
//			
//			switch(job.title) {
//				case "AGENT": return 2; 
//				case "MANAGER": return 3; 
//				case "CEO": return 4; 
//		        default: return 1;
//			}
//		}
//
//		public String getSSN() {
//			return SSN;
//		}
//
//		public void setSSN(String SSN) {
//			this.SSN = SSN;
//		}
//
//		@Override
//		public String toString() {
//			return "Employee [SSN=" + SSN + ", id=" + id + ", fname=" + fname + ", lname=" + lname + ", email=" + email
//					+ ", address=" + address.toString() + ", dob=" + dob + "]";
//		}
//
//		@Override
//		public int hashCode() {
//			final int prime = 31;
//			int result = super.hashCode();
//			result = prime * result + ((SSN == null) ? 0 : SSN.hashCode());
//			return result;
//		}
//
//		@Override
//		public boolean equals(Object obj) {
//			if (this == obj)
//				return true;
//			if (!super.equals(obj))
//				return false;
//			if (getClass() != obj.getClass())
//				return false;
//			Employee other = (Employee) obj;
//			if (SSN == null) {
//				if (other.SSN != null)
//					return false;
//			} else if (!SSN.equals(other.SSN))
//				return false;
//			return true;
//		}
//			
//}
