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
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.carty.model.User;

@Entity
public class HealthPolicy implements Serializable{

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
	
	@OneToOne  (mappedBy="hpolicy") 
	protected User user;
	
	@ManyToOne
	protected HealthPDB details;
	
	@OneToMany(cascade=CascadeType.ALL)
    @JoinTable(name="HealthPolicy_Account", joinColumns={@JoinColumn(name="policy_id", referencedColumnName="id")}
    , inverseJoinColumns={@JoinColumn(name="account_id", referencedColumnName="id")})
    private List<Account> accounts = new ArrayList<>();
	
		
		public HealthPolicy() {
			
		}
		
		public HealthPolicy(double insuredValue) {
			super();
			this.creationDate = new Date(System.currentTimeMillis());
			this.insuredValue = insuredValue;  //some amount times premium
			this.premium = insuredValue*costFactor; //some amount times cost factor

		}
		
		public HealthPolicy(double insuredValue, HealthPDB hpdb) {
			super();
			this.creationDate = new Date(System.currentTimeMillis());
			this.insuredValue = insuredValue;  //some amount times premium
			this.premium = insuredValue*costFactor; //some amount times cost factor
			this.details = hpdb;

		}


		
		public HealthPolicy(double costFactor, double insuredValue) {
			super();
			this.creationDate = new Date(System.currentTimeMillis());
			this.costFactor = costFactor;
			this.insuredValue = insuredValue;  //some amount times premium
			this.premium = insuredValue*costFactor; //some amount times cost factor

		}
		
		public HealthPolicy(double premium, double costFactor, double insuredValue) {
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
  
		public void setDetails(HealthPDB hpdb) {
			this.details = hpdb;
		}

		public long getHealthid() {
			return id;
		}

		public void setHealthid(int healthid) {
			this.id = healthid;
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

		public boolean getApproved() {
			return approved;
		}

		public void setApproved(boolean approved) {
			this.approved = approved;
		}

		public boolean getActive() {
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

		public HealthPDB getDetails() {
			return details;
		}

		public void setId(long id) {
			this.id = id;
		}

		

		@Override
		public String toString() {
			return "HealthPolicy [id=" + id + ", creationDate=" + creationDate + ", insuredValue=" + insuredValue
					+ ", costFactor=" + costFactor + ", premium=" + premium + ", approved=" + approved + ", active="
					+ active + ", details=" + details + ", accounts=" + accounts + "]";
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
