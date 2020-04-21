package com.carty.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

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
	
	@OneToMany(cascade=CascadeType.ALL)  //for join with the Employee Table/Entity
	@JoinColumn(name="job_id")
	protected List<Employee> employees = new ArrayList<>();
	
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

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	@Override
	public String toString() {
		return "Job [id=" + id + ", title=" + title + ", baseSalary=" + baseSalary + ", commission=" + commission + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(baseSalary);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(commission);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + id;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		
		Job other = (Job) obj;
		if (id != other.id)
			return false;
		else
			return true;
	}
	
	
}
