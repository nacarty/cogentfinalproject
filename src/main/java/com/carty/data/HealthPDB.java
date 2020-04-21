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
public class HealthPDB implements Serializable{

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
	
	@OneToMany(cascade=CascadeType.ALL)  //with Foreign Key Relationship
    @JoinColumn(name="pdetails_id")
	protected Set<HealthPolicy> policies;
}
