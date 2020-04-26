package com.carty.data;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class InsuredVehicle{  //we can implement serializable interface on all Entity classes

	@Transient
	private static final long serialVersionUID = 7751381036533160950L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected long id;
	
	@Column(unique=true, length = 45)
	protected String vin;
	
	@Column(nullable = false, length = 45)
	protected String color;
	
	@Column(nullable = false, precision = 2)
	protected double value = 0.0;
	
	protected Date valuationDate = new Date(System.currentTimeMillis());

	@OneToOne(mappedBy="vehicle")
	protected VehiclePolicy vehiclePolicy;
	
	@ManyToOne
	@JoinColumn(name="vehicle_brand")
	protected Vehicle vehicleBrand;
	
	public InsuredVehicle(){
		
	}
	
	public InsuredVehicle(String vinNumber, String color, double value, Vehicle veh) {
		super();
		this.vin = vinNumber;
		this.color = color;
		this.value = value;
		this.vehicleBrand = veh;
	}
	
	public InsuredVehicle(String vinNumber, String color, double value, Date valuationDate) {
		super();
		this.vin = vinNumber;
		this.color = color;
		this.value = value;
		this.valuationDate = valuationDate;
	}
	
	public InsuredVehicle(String vinNumber, String color, double value, Date valuationDate, VehiclePolicy vehiclePolicy) {
		super();
		this.vin = vinNumber;
		this.color = color;
		this.value = value;
		this.valuationDate = valuationDate;
		this.vehiclePolicy = vehiclePolicy;
	}

	public String getVIN() {
		return vin;
	}

	public void setVIN(String vIN) {
		vin = vIN;
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

	/*
	public VehiclePolicy getVehiclePolicy() {
		return vehiclePolicy;
	}

	public void setVehiclePolicy(VehiclePolicy vehiclePolicy) {
		this.vehiclePolicy = vehiclePolicy;
	} */
	

	public Vehicle getVehicleBrand() {
		return vehicleBrand;
	} 

	public void setVehicleBrand(Vehicle vehicleBrand) {
		this.vehicleBrand = vehicleBrand;
	}

	
	@Override
	public String toString() {
		return "InsuredVehicle [id=" + id + ", vin=" + vin + ", color=" + color + ", value=" + value
				+ ", valuationDate=" + valuationDate + ", vehicleBrand=" + vehicleBrand + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((vin == null) ? 0 : vin.hashCode());
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
		if (vin == null) {
			if (other.vin != null)
				return false;
		} else if (!vin.equals(other.vin))
			return false;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		return true;
	}
	
		
}
