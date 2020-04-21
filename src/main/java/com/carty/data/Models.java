package com.carty.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="models")
public class Models implements Serializable{
	
	/**
	 * 
	 */
	@Transient
	private static final long serialVersionUID = 1504482147571667745L;

	@Id
	@Column(nullable=false, unique = true)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected int modelid;
	
	@Column(nullable=false)
	protected String make;
	
	@Column(nullable=false, unique = true)
	protected String model;
	
	public Models(){
		
	}

	public Models(Make make, String model) {
		this.make = make.make;
		this.model = model;
	}
	
	public Models(String make, String model) {
		super();
		this.make = make;
		this.model = model;
	}
	
	public Models(int modelid, String make, String model) {
		super();
		this.modelid = modelid;
		this.make = make;
		this.model = model;
	}

	public int getModelid() {
		return modelid;
	}

	public void setModelid(int modelid) {
		this.modelid = modelid;
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

	@Override
	public String toString() {
		return "Models [modelid=" + modelid + ", make=" + make + ", model=" + model + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((make == null) ? 0 : make.hashCode());
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result + modelid;
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
		
		Models other = (Models) obj;
		
		if (modelid != other.modelid)
			return false;
		return true;
	}
	
	
	
	
}
