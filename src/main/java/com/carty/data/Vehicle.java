package com.carty.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="vehicles")
public class Vehicle implements Serializable{

	/**
	 * 
	 */
	@Transient
	private static final long serialVersionUID = -4806713303871080210L;

	@Id
	@GeneratedValue
	protected long vehicleId;
	
	@Column(nullable = false, length = 4)
	protected int manYear; //year of manufacture //we can make this a date
	
	@Column(nullable = false, length = 45)
	protected String make;
	
	@Column(nullable = false, length = 45)
	protected String model;
	
	@Column(nullable = false, precision = 2)
	protected double price;
	
	public Vehicle(){
		
	}

	public Vehicle(int manYear, String make, String model, double price) {
		this.manYear = manYear;
		this.make = make;
		this.model = model;
		this.price = price;
	}

	
	public Vehicle(int vehicleId, int manYear, String make, String model, double price) {
		this.vehicleId = vehicleId;
		this.manYear = manYear;
		this.make = make;
		this.model = model;
		this.price = price;
	}

	public long getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(long vehicleId) {
		this.vehicleId = vehicleId;
	}

	public int getManYear() {
		return manYear;
	}

	public void setManYear(int manYear) {
		this.manYear = manYear;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Vehicle [vehicleId=" + vehicleId + ", manYear=" + manYear + ", make=" + make + ", model=" + model
				+ ", price=" + price + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((make == null) ? 0 : make.hashCode());
		result = prime * result + manYear;
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result + (int) (vehicleId ^ (vehicleId >>> 32));
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
		
		Vehicle other = (Vehicle) obj;
		if (this.vehicleId != other.vehicleId)
			return false;
		else 
			return true;
	}	
	
	
}
