package com.mypoc.userdetailtracker.user.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="User_details")
public class Users {
	
	public Users() {
		
	}
	
	public Users(int id, String name, String surname, int pincode, Date birth_Date, Date date_Of_Joining) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.pincode = pincode;
		this.birth_Date = birth_Date;
		this.date_Of_Joining = date_Of_Joining;
	}
	
	public Users(String name, String surname, int pincode, Date birth_Date, Date date_Of_Joining) {
		super();
		this.name = name;
		this.surname = surname;
		this.pincode = pincode;
		this.birth_Date = birth_Date;
		this.date_Of_Joining = date_Of_Joining;
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
	@Column(name = "BIRTH_DATE")
	private Date birth_Date;
	@Column(name = "DATE_OF_JOINING")
	private Date date_Of_Joining;
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
		return birth_Date;
	}
	public Date getDate_Of_Joining() {
		return date_Of_Joining;
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
	public void setBirth_Date(Date birth_Date) {
		this.birth_Date = birth_Date;
	}
	public void setDate_Of_Joining(Date date_Of_Joining) {
		this.date_Of_Joining = date_Of_Joining;
	}

	
	
	
		
}
