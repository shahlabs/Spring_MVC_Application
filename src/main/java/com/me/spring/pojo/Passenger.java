package com.me.spring.pojo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name="passenger")
public class Passenger {


	@Id 
	@GeneratedValue
	@Column(name="passenger_id", unique = true, nullable = false)
	long id;
	
	@Column(name="firstName")
	String firstName;
	
	@Column(name="lastName")
	String lastName;
	
	@Column(name="gender")
	String gender;
	
	@Column(name="email")
	String email;
	
	@Column(name="dob")
	String dob;
	
	@Column(name="phonenum")
	String phonenum;
	
	@Column(name="address")
	String address;
	
	//MultipartFile photo;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="creditCardNumber")
	Payment payment;
	
	
	
	Passenger(){
		
	}
	
	
	public Passenger(String firstName, String lastName, String gender, String email, String dob, String phonenum,
			String address) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.email = email;
		this.dob = dob;
		this.phonenum = phonenum;
		this.address = address;
		
	}




	public Payment getPayment() {
		return payment;
	}


	public void setPayment(Payment payment) {
		this.payment = payment;
	}


	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getPhonenum() {
		return phonenum;
	}
	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
//	public MultipartFile getPhoto() {
//		return photo;
//	}
//	public void setPhoto(MultipartFile photo) {
//		this.photo = photo;
//	}

	
	
	
	
	
}
