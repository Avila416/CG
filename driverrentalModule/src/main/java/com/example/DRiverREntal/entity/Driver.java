package com.example.DRiverREntal.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
//Entity class representing the Driver
@Entity
public class Driver {
	// Primary key for Driver entity (auto-generated)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int driverId;
    private String firstName;
    private String lastName;
    private String address;
    private String mobileNumber;
    private String emailId;
    private String licenseNo;
	public int getDriverId() {
		return driverId;
	}
	public void setDriverId(int driverId) {
		this.driverId = driverId;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getLicenseNo() {
		return licenseNo;
	}
	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}
	// Parameterized constructor to initialize Driver object
	public Driver(int driverId, String firstName, String lastName, String address, String mobileNumber, String emailId,
			String licenseNo) {
		super();
		this.driverId = driverId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.mobileNumber = mobileNumber;
		this.emailId = emailId;
		this.licenseNo = licenseNo;
	}
	// Default constructor for JPA
	public Driver() {
		super();
	}
   
   
	
}

