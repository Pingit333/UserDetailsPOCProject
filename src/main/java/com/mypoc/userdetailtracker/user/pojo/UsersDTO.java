package com.mypoc.userdetailtracker.user.pojo;


import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class UsersDTO{
	
	

	public UsersDTO() {
		super();
	}

	public UsersDTO(int id, String name, String surname, int pincode, Date birthDate, Date dateOfJoining,
			boolean deleted) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.pincode = pincode;
		this.birthDate = birthDate;
		this.dateOfJoining = dateOfJoining;
		this.deleted = deleted;
	}

	public UsersDTO(String name, String surname, int pincode, Date birthDate, Date dateOfJoining,
			boolean deleted) {
		super();
		this.name = name;
		this.surname = surname;
		this.pincode = pincode;
		this.birthDate = birthDate;
		this.dateOfJoining = dateOfJoining;
		this.deleted = deleted;
	}
	
	private int id;
	
	private String name;
	
	private String surname;
	
	private int pincode;

	private Date birthDate;
	
	private Date dateOfJoining;
	
	private boolean deleted = Boolean.FALSE;

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

	public Date getBirthDate() {
		return birthDate;
	}

	public Date getDateOfJoining() {
		return dateOfJoining;
	}

	public boolean isDeleted() {
		return deleted;
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

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public void setDateOfJoining(Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", name=" + name + ", surname=" + surname + ", pincode=" + pincode + ", birthDate="
				+ birthDate + ", dateOfJoining=" + dateOfJoining + ", deleted=" + deleted + "]";
	}

	
	
	
	
		
}
