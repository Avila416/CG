package com.capgemini.dto;

import java.util.List;
import java.util.Set;

public class UserDTO {
	
	
    private Long userId;
	
	private String username;
	
	private String password;
	
	private Long userPhone;
	
	private String email;
	
	private Set<String> roles;

	private List<BookingDTO> bookings; // List to hold associated booking details

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(Long userPhone) {
		this.userPhone = userPhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

	public List<BookingDTO> getBookings() {
		return bookings;
	}

	public void setBookings(List<BookingDTO> bookings) {
		this.bookings = bookings;
	}


}
