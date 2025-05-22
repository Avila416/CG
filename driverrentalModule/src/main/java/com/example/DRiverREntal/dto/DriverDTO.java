package com.example.DRiverREntal.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
//Data Transfer Object (DTO) for Driver entity,Used to transfer driver data between layers.
public class DriverDTO {
	// Unique identifier for the driver
    private int driverId;
    // Driver's first name (2-10 characters, cannot be blank)
    @NotBlank(message = "{validation.firstName.blank}")
    @Size(min = 2, max = 10, message = "{validation.firstName.size}")
    private String firstName;
    // Driver's last name (2-10 characters, cannot be blank)
    @NotBlank(message = "{validation.lastName.blank}")
    @Size(min = 2, max = 10, message = "{validation.lastName.size}")
    private String lastName;
    // Driver's address (5-20 characters, cannot be blank)
    @NotBlank(message = "{validation.address.blank}")
    @Size(min = 5, max = 20, message = "{validation.address.size}")
    private String address;
    // Driver's mobile number (exactly 10 digits)
    @Pattern(regexp = "^[0-9]{10}$", message = "{validation.mobileNumber.pattern}")
    private String mobileNumber;
    // Driver's email (must be in proper format and only @gmail.com is allowed)
    @NotBlank(message = "{validation.emailID.blank}")
    @Email(message = "{validation.emailID.email}")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@gmail\\.com$", message = "{validation.emailID.pattern}")
    private String emailId;
    // Driver's license number (5-15 characters, cannot be blank)
    @NotBlank(message = "{validation.licenseNo.blank}")
    @Size(min = 5, max = 15, message = "{validation.licenseNo.size}")
    private String licenseNo;
    // Getter for driverId
	public int getDriverId() {
		return driverId;
	}
	// Setter for driverId
	public void setDriverId(int driverId) {
		this.driverId = driverId;
	}
	// Getter for firstName
	public String getFirstName() {
		return firstName;
	}
	// Setter for firstName
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	// Getter for lastName
	public String getLastName() {
		return lastName;
	}
	// Setter for lastName
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	 // Getter for address
	public String getAddress() {
		return address;
	}
	// Setter for address
	public void setAddress(String address) {
		this.address = address;
	}
	// Getter for mobileNumber
	public String getMobileNumber() {
		return mobileNumber;
	}
	// Setter for mobileNumber
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	// Getter for emailId
	public String getEmailId() {
		return emailId;
	}
	// Setter for emailId
	public void setEmailID(String emailId) {
		this.emailId = emailId;
	}
	// Getter for licenseNo
	public String getLicenseNo() {
		return licenseNo;
	}
	// Setter for licenseNo
	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}
    //Constructor to initialize all fields of DriverDTO.
	public DriverDTO(int driverId,
			@NotBlank(message = "{validation.firstName.blank}") @Size(min = 2, max = 10, message = "{validation.firstName.size}") String firstName,
			@NotBlank(message = "{validation.lastName.blank}") @Size(min = 2, max = 10, message = "{validation.lastName.size}") String lastName,
			@NotBlank(message = "{validation.address.blank}") @Size(min = 5, max = 20, message = "{validation.address.size}") String address,
			@Pattern(regexp = "^[0-9]{10}$", message = "{validation.mobileNumber.pattern}") String mobileNumber,
			@NotBlank(message = "{validation.emailId.blank}") @Email(message = "{validation.emailId.email}") @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@gmail\\.com$", message = "{validation.emailId.pattern}") String emailId,
			@NotBlank(message = "{validation.licenseNo.blank}") @Size(min = 5, max = 15, message = "{validation.licenseNo.size}") String licenseNo) {
		super();
		this.driverId = driverId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.mobileNumber = mobileNumber;
		this.emailId = emailId;
		this.licenseNo = licenseNo;
	}
}


