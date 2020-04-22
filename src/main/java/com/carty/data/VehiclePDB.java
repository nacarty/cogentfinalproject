package com.carty.data;

import java.io.Serializable;
import java.util.Set;

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
public class VehiclePDB implements Serializable{

	@Transient
	private static final long serialVersionUID = -2915660337745506022L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected long id;
	
	@Column(nullable=false, length=45)
	protected String name;
	
	@Column(nullable=false)
	protected String description;
	
	@Column(nullable=false, precision=2)
	protected double basePremium;
	
	@Column(nullable=false)
	protected boolean active = true;
	
	@OneToMany(cascade=CascadeType.ALL)  //ManyToOne with Foreign Key relation
    @JoinColumn(name="pol_details_id")
	protected Set<VehiclePolicy> policies;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getBasePremium() {
		return basePremium;
	}

	public void setBasePremium(double basePremium) {
		this.basePremium = basePremium;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Set<VehiclePolicy> getPolicies() {
		return policies;
	}

	public void setPolicies(Set<VehiclePolicy> policies) {
		this.policies = policies;
	}
	
	
}
