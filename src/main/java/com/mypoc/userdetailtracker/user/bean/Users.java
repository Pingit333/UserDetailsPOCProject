package com.mypoc.userdetailtracker.user.bean;





import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

@Entity
@Table(name="User_details")
public class Users{
	
	public Users() {
		
	}
	
	public Users(@Length(min = 3, max = 15) @NotBlank(message = "Name is mandatory") String name,
			@Length(min = 3, max = 15) @NotBlank(message = "Surname is mandatory") String surname,
			@NotNull @Range(min = 100000, max = 999999) int pincode, @NotNull @PastOrPresent Date birthDate,
			@NotNull @PastOrPresent Date dateOfJoining, @NotNull boolean deleted) {
		this.name = name;
		this.surname = surname;
		this.pincode = pincode;
		this.birthDate = birthDate;
		this.dateOfJoining = dateOfJoining;
		this.deleted = deleted;
	}

	public Users(@NotNull int id, @Length(min = 3, max = 15) @NotBlank(message = "Name is mandatory") String name,
			@Length(min = 3, max = 15) @NotBlank(message = "Surname is mandatory") String surname,
			@NotNull @Range(min = 100000, max = 999999) int pincode, @NotNull @PastOrPresent Date birthDate,
			@NotNull @PastOrPresent Date dateOfJoining, @NotNull boolean deleted) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.pincode = pincode;
		this.birthDate = birthDate;
		this.dateOfJoining = dateOfJoining;
		this.deleted = deleted;
	}
	
	@Id
	@NotNull
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	
	@Column(name = "NAME" )
	@Length(min = 3, max = 15)
	@NotBlank(message = "Name is mandatory")
	private String name;
	
	@Column(name = "SURNAME")
	@Length(min = 3, max = 15)
	@NotBlank(message = "Surname is mandatory")
	private String surname;
	
	@NotNull
	@Column(name = "PINCODE")
	@Range(min = 100000,max=999999)
	private int pincode;

	@NotNull
	@PastOrPresent
	@Column(name = "BIRTHDATE")
	private Date birthDate;
	
	@NotNull
	@PastOrPresent
	@Column(name = "DOJ")
	private Date dateOfJoining;
	
	@NotNull
	@Column(name="DELETED")
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
	
	
	
		
}
