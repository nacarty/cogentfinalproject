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

@Entity
public class Address implements Serializable{
    
	@Transient
	private static final long serialVersionUID = 1468293254054854791L;

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
    
    @Column( nullable = false, length = 45)
	private String street;
    
    @Column(nullable = true, length = 10)
	private String unit;
    
    @Column(nullable = false, length = 45)
	private String city;
    
    @Column(nullable = false, length = 45)
	private String state;
    
    @Column(nullable = false, length = 5)
	private String zip;
    
    @Column(nullable = true, length = 4)
	private String zipplus;
	
	@OneToOne(mappedBy="address")
	protected Branch branch;
	
	@OneToOne  (mappedBy="address")  //this would be correct if I wanted it
	protected User user;
	
	public Address() {
		
	}
	
	public Address(String street, String city, String state, String zip) {
		super();
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.zipplus = "0000";
	}
	
	
	public Address(String street, String unit, String city, String state, String zip, String zipplus) {
		super();
		this.street = street;
		this.unit = unit;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.zipplus = zipplus;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getZipplus() {
		return zipplus;
	}

	public void setZipplus(String zipplus) {
		this.zipplus = zipplus;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", street=" + street + ", unit=" + unit + ", city=" + city + ", state=" + state
				+ ", zip=" + zip + ", zipplus=" + zipplus + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((street == null) ? 0 : street.hashCode());
		result = prime * result + ((unit == null) ? 0 : unit.hashCode());
		result = prime * result + ((zip == null) ? 0 : zip.hashCode());
		result = prime * result + ((zipplus == null) ? 0 : zipplus.hashCode());
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
		
		Address other = (Address) obj;
		if (this.id != other.id)
			return false;
		else 
			return true;
	}
	
	
}
