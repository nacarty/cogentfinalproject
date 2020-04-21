package com.carty.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="books")
public class Books {

	@Id
	private int bkId;
	private String bkName;
	private String bkAuthor;
	
	Books(){ //Empty Constructor required
		
	}
	Books(int bkId, String bkName, String bkAuthor) {
		this.bkId = bkId;
		this.bkName = bkName;
		this.bkAuthor = bkAuthor;
	}
	public int getBkId() {
		return bkId;
	}
	public void setBkId(int bkId) {
		this.bkId = bkId;
	}
	public String getBkName() {
		return bkName;
	}
	public void setBkName(String bkName) {
		this.bkName = bkName;
	}
	public String getBkAuthor() {
		return bkAuthor;
	}
	public void setBkAuthor(String bkAuthor) {
		this.bkAuthor = bkAuthor;
	}
	@Override
	public String toString() {
		return "Books [bkId=" + bkId + ", bkName=" + bkName + ", bkAuthor=" + bkAuthor + "]";
	}
	
	
}
