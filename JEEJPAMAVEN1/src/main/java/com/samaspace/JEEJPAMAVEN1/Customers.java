package com.samaspace.JEEJPAMAVEN1;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name= "customers")
public class Customers implements Serializable{
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id" , unique = true)
	private int cust_id;
	
	
	@Column(name="firstname" , nullable = false)
	private String FName;
	
	@Column(name="lastname" , nullable = false)
	private String LName;

	public int getCust_id() {
		return cust_id;
	}

	public void setCust_id(int cust_id) {
		this.cust_id = cust_id;
	}

	public String getFName() {
		return FName;
	}

	public void setFName(String fName) {
		FName = fName;
	}

	public String getLName() {
		return LName;
	}

	public void setLName(String lName) {
		LName = lName;
	}
	
	
	
	
	
	

}
