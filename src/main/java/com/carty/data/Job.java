package com.carty.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.carty.model.User;
import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
public class Job implements Serializable {

	
	@Transient
	private static final long serialVersionUID = 4251991558261545397L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(unique = true, nullable = false, length = 45)
	protected String title;
	
	@Column(nullable = false, precision = 2)
	protected double baseSalary = 2000;
	
	@Column(nullable = false, precision = 2)
	protected double commission = 0.05; //eg 0.12 for 12%
	
	@OneToOne
	@JsonBackReference
	protected User user;
	
	
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
  
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
		
}
