package com.carty.data;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
public class VehiclePolicy implements Serializable{

	@Transient
	private static final long serialVersionUID = -8422402984159832881L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected long id;
	
	@Column(nullable = false)
	protected Date creationDate = new Date(System.currentTimeMillis());
	
	@Column(nullable = false, precision = 2)
	protected double insuredValue;  //
	
	@Column(nullable = false, precision = 2)
	protected double costFactor = 0.05/12;  // 5% over 12
	
	@Column(nullable = false, precision = 2)
	protected double premium; //to be calculated
	
	@Column(nullable = false)
	protected boolean approved = false; //to be approved by Manager
	
	@Column(nullable = false)
	protected boolean active = false; //to be approved by Manager
	
	@OneToMany(cascade=CascadeType.ALL)
    @JoinTable(name="VehiclePolicy_Account", joinColumns={@JoinColumn(name="policy_id", referencedColumnName="id")}
    , inverseJoinColumns={@JoinColumn(name="account_id", referencedColumnName="id")})
    private List<Account> accounts = new ArrayList<>();
	
	
	@ManyToOne
    //@JsonBackReference //this was helpful in preventing infinite recursion in my retrieved json. However, removing the getVehiclePolicies in the VehiclePDB.java class also did it 
	protected VehiclePDB details;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="veh_insured")
	protected InsuredVehicle vehicle;
	
		
		public VehiclePolicy() {
			
		}
		
		public VehiclePolicy(double insuredValue) {
			super();
			this.creationDate = new Date(System.currentTimeMillis());
			this.insuredValue = insuredValue;  //some amount times premium
			this.premium = insuredValue*costFactor; //some amount times cost factor

		}

		public VehiclePolicy(VehiclePDB vehPDB, double insuredValue, InsuredVehicle iVeh) {
			super();
			this.creationDate = new Date(System.currentTimeMillis());
			this.insuredValue = insuredValue;  //some amount times premium
			this.premium = insuredValue*costFactor; //some amount times cost factor
			this.details = vehPDB;
			this.vehicle = iVeh;
		}

		public VehiclePolicy(double costFactor, double insuredValue) {
			super();
			this.creationDate = new Date(System.currentTimeMillis());
			this.costFactor = costFactor;
			this.insuredValue = insuredValue;  //some amount times premium
			this.premium = insuredValue*costFactor; //some amount times cost factor

		}
		
		public VehiclePolicy(double premium, double costFactor, double insuredValue) {
			super();
			this.creationDate = new Date(System.currentTimeMillis());
			this.costFactor = costFactor;
			this.insuredValue = insuredValue;  //some amount times premium
			this.premium = premium; //some amount times cost factor

		}

		public double getPremium() {
			return premium;
		}

		public void setPremium(double premium) {
			this.premium = premium;
		}

		
		public Date getCreationDate() {
			return creationDate;
		}

		public void setCreationDate(Date creationDate) {
			this.creationDate = creationDate;
		}

		public double getInsuredValue() {
			return insuredValue;
		}

		public void setInsuredValue(double insuredValue) {
			this.insuredValue = insuredValue;
		}
/*
		public void setId(long id) {
			this.id = id;
		}
*/
		public void setPolicyId(int pid) {
			this.id = pid;
		}		
		
		long getPolicyId() {
			return this.id;
		}


		public long getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}
		
		

		public double getCostFactor() {
			return costFactor;
		}

		public void setCostFactor(double costFactor) {
			this.costFactor = costFactor;
		}

		public boolean isApproved() {
			return approved;
		}

		public void setApproved(boolean approved) {
			this.approved = approved;
		}

		public boolean isActive() {
			return active;
		}

		public void setActive(boolean active) {
			this.active = active;
		}

		public List<Account> getAccounts() {
			return accounts;
		}

		public void setAccounts(List<Account> accounts) {
			this.accounts = accounts;
		}

		public VehiclePDB getDetails() {
			return details;
		}

		public void setDetails(VehiclePDB details) {
			this.details = details;
		}

		public InsuredVehicle getVehicle() {
			return vehicle;
		}

		public void setVehicle(InsuredVehicle vehicle) {
			this.vehicle = vehicle;
		}

		public void setId(long id) {
			this.id = id;
		}

		@Override
		public String toString() {
			return "VehiclePolicy [id=" + id + ", creationDate=" + creationDate + ", insuredValue=" + insuredValue
					+ ", costFactor=" + costFactor + ", premium=" + premium + ", approved=" + approved + ", active="
					+ active + ", accounts=" + accounts + ", details=" + details + ", vehicle=" + vehicle + "]";
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
			result = prime * result + (int) (id ^ (id >>> 32));
			long temp;
			temp = Double.doubleToLongBits(insuredValue);
			result = prime * result + (int) (temp ^ (temp >>> 32));
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
			
			HealthPolicy other = (HealthPolicy) obj;
			
			if (creationDate == null) {
				if (other.creationDate != null)
					return false;
			} else if (!creationDate.equals(other.creationDate))
				return false;
			if (id != other.id)
				return false;
			if (Double.doubleToLongBits(insuredValue) != Double.doubleToLongBits(other.insuredValue))
				return false;
			return true;
		}

		
		
			
}
