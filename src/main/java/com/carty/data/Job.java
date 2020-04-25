package com.carty.data;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.carty.model.User;

@Entity
public class Job implements Serializable {

	/**
	 * 
	 */
	@Transient
	private static final long serialVersionUID = 4251991558261545397L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(unique = true, nullable = false, length = 45)
	protected String title;
	
	@Column(nullable = false, precision = 2)
	protected double baseSalary;
	
	@Column(nullable = false, precision = 2)
	protected double commission; //eg 0.12 for 1%
	
	@OneToMany(cascade=CascadeType.ALL)
    @JoinTable(name="job_user", joinColumns={@JoinColumn(name="job_id", referencedColumnName="id")}
    , inverseJoinColumns={@JoinColumn(name="user_id", referencedColumnName="id")})	protected List<User> users;
	
	public Job(){
		
	}

	public Job(String title, double baseSalary, double commission) {
		super();
		this.title = title;
		this.baseSalary = baseSalary;
		this.commission = commission;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getBaseSalary() {
		return baseSalary;
	}

	public void setBaseSalary(double baseSalary) {
		this.baseSalary = baseSalary;
	}

	public double getCommission() {
		return commission;
	}

	public void setCommission(double commission) {
		this.commission = commission;
	}

	@OneToMany(cascade=CascadeType.ALL)
    @JoinTable(name="job_user", joinColumns={@JoinColumn(name="job_id", referencedColumnName="id")}
    , inverseJoinColumns={@JoinColumn(name="user_id", referencedColumnName="id")})
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

		
}
