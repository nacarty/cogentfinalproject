package com.carty.data;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
public class InsuredVehicle{  //we can implement serializable interface on all Entity classes

	@Transient
	private static final long serialVersionUID = 7751381036533160950L;

	@Id
	protected long id;
	
	@Column(unique=true, length = 45)
	protected String VIN;
	
	@Column(nullable = false, length = 45)
	protected String color;
	
	@Column(nullable = false, precision = 2)
	protected double value;
	
	protected Date valuationDate = new Date(System.currentTimeMillis());

	@OneToOne
	protected VehiclePolicy vehiclePolicy;
	
	@ManyToOne
	protected Vehicle vehicleBrand;
	
	public InsuredVehicle(){
		
	}
	
	public InsuredVehicle(String vinNumber, String color, double value, Date valuationDate) {
		super();
		this.VIN = vinNumber;
		this.color = color;
		this.value = value;
		this.valuationDate = valuationDate;
	}
	
	public InsuredVehicle(String vinNumber, String color, double value, Date valuationDate, VehiclePolicy vehiclePolicy) {
		super();
		this.VIN = vinNumber;
		this.color = color;
		this.value = value;
		this.valuationDate = valuationDate;
		this.vehiclePolicy = vehiclePolicy;
	}

	public String getVIN() {
		return VIN;
	}

	public void setVIN(String vIN) {
		VIN = vIN;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public Date getValuationDate() {
		return valuationDate;
	}

	public void setValuationDate(Date valuationDate) {
		this.valuationDate = valuationDate;
	}

	public VehiclePolicy getVehiclePolicy() {
		return vehiclePolicy;
	}

	public void setVehiclePolicy(VehiclePolicy vehiclePolicy) {
		this.vehiclePolicy = vehiclePolicy;
	}
	

	public Vehicle getVehicleBrand() {
		return vehicleBrand;
	}

	public void setVehicleBrand(Vehicle vehicleBrand) {
		this.vehicleBrand = vehicleBrand;
	}

	@Override
	public String toString() {
		return "InsuredVehicle [VIN=" + VIN + ", color=" + color + ", value=" + value + ", valuationDate="
				+ valuationDate + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((VIN == null) ? 0 : VIN.hashCode());
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		InsuredVehicle other = (InsuredVehicle) obj;
		if (VIN == null) {
			if (other.VIN != null)
				return false;
		} else if (!VIN.equals(other.VIN))
			return false;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		return true;
	}
	
		
}
