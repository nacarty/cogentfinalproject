package com.carty.data;


import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;


@Entity
public class Account implements Serializable{

	/**
	 * 
	 */
	@Transient
	private static final long serialVersionUID = -6128918637074355729L;

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(unique = true, nullable = false, length = 10)
	private String accountNumber;
	
	@Column(nullable = false, precision = 2)
	private double amountPaid = 0.0;
	
	@Column( nullable = false, precision = 2)
	private double amountBalance = 0.0;
	
	
	private Timestamp paymentDate = new Timestamp (System.currentTimeMillis());
	
	
	public Account(){
		
	}
	
	public Account(String accountNumber, double amountPaid) {
		super();
		this.accountNumber = accountNumber;
		this.amountPaid = amountPaid;
		this.amountBalance -= this.amountPaid;
		this.paymentDate = new Timestamp (System.currentTimeMillis());
	}

	public Account(String accountNumber, double amountPaid, double amountBalance) {
		this.accountNumber = accountNumber;
		this.amountPaid = amountPaid;
		this.amountBalance = amountBalance;
		this.paymentDate = new Timestamp (System.currentTimeMillis());
	}
	
	public Account(String accountNumber, double amountPaid, double amountBalance, Timestamp paymentDate) {
		this.accountNumber = accountNumber;
		this.amountPaid = amountPaid;
		this.amountBalance = amountBalance;
		this.paymentDate = paymentDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(double amountPaid) {
		this.amountPaid = amountPaid;
	}

	public double getAmountBalance() {
		return amountBalance;
	}

	public void setAmountBalance(double amountBalance) {
		this.amountBalance = amountBalance;
	}

	public Timestamp getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Timestamp paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", accountNumber=" + accountNumber + ", amountPaid=" + amountPaid
				+ ", amountBalance=" + amountBalance + ", paymentDate=" + paymentDate + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountNumber == null) ? 0 : accountNumber.hashCode());
		long temp;
		temp = Double.doubleToLongBits(amountBalance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(amountPaid);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((paymentDate == null) ? 0 : paymentDate.hashCode());
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
		
		Account other = (Account) obj;
		if (this.id != other.id)
			return false;
		else 
			return true;
	}	
	
}
