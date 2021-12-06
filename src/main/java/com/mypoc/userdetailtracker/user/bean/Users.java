package com.mypoc.userdetailtracker.user.bean;



import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="User_details")
public class Users implements Comparable<Users>{
	
	public Users() {
		
	}
	
	public Users(int id, String name, String surname, int pincode, Date birthDate, Date dateOfJoining) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.pincode = pincode;
		this.birthDate = birthDate;
		this.dateOfJoining = dateOfJoining;
	}
	
	public Users(String name, String surname, int pincode, Date birthDate, Date dateOfJoining) {
		super();
		this.name = name;
		this.surname = surname;
		this.pincode = pincode;
		this.birthDate = birthDate;
		this.dateOfJoining = dateOfJoining;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name = "NAME")
	private String name;
	@Column(name = "SURNAME")
	private String surname;
	@Column(name = "PINCODE")
	private int pincode;
	@Column(name = "BIRTHDATE",columnDefinition = "DATE")
	private Date birthDate;
	@Column(name = "DOJ",columnDefinition = "DATE")
	private Date dateOfJoining;
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getSurname() {
		return surname;
	}
	public int getPincode() {
		return pincode;
	}
	public Date getBirth_Date() {
		return birthDate;
	}
	public Date getDate_Of_Joining() {
		return dateOfJoining;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
	public void setBirth_Date(Date birthDate) {
		this.birthDate = birthDate;
	}
	public void setDate_Of_Joining(Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	@Override
	public int compareTo(Users o) {
		 if (o.getId() > this.getId()) {
			   return 1;
			  } else if (o.getId() < this.getId()) {
			   return -1;
			  }
			  return 0;
			 
	}

	
	
	
		
}
