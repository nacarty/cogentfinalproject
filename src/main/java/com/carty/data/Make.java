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
@Table(name="makes")
public class Make implements Serializable{
	
	/**
	 * 
	 */
	@Transient
	private static final long serialVersionUID = -2787230970549118199L;

	@Id
	@Column(nullable=false, unique = true)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected int makeid;
	
	@Column(nullable=false, unique = true, length = 45)
	protected String make;


	public Make(){
		
	}
	
	public Make(String make) {
		super();
		this.make = make;
	}
	
	public Make(int makeid, String make) {
		super();
		this.makeid = makeid;
		this.make = make;
	}

	public int getMakeid() {
		return makeid;
	}

	public void setMakeid(int makeid) {
		this.makeid = makeid;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	@Override
	public String toString() {
		return "Make [makeid=" + makeid + ", make=" + make + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((make == null) ? 0 : make.hashCode());
		result = prime * result + makeid;
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
		
		Make other = (Make) obj;

		if (makeid != other.makeid)
			return false;
		return 
			true;
	}
	
	
	
}
