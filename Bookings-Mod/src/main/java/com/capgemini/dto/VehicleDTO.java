package com.capgemini.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.util.List;

public class VehicleDTO {
	
	 private int vehicleId;
	    
	    private String brand;

		private Integer driverId;
	    private String model;
	    	    
	    private String type; // Car, Bike, Truck
	    
	    private String registrationNumber;

	    @Enumerated(EnumType.STRING)
	    private VehicleStatus status; // AVAILABLE, BOOKED, MAINTENANCE

	private List<BookingDTO> bookings; // List to hold associated booking details



	public int getVehicleId() {
			return vehicleId;
		}

		public void setVehicleId(int vehicleId) {
			this.vehicleId = vehicleId;
		}

		public String getBrand() {
			return brand;
		}

		public void setBrand(String brand) {
			this.brand = brand;
		}

		public String getModel() {
			return model;
		}

		public void setModel(String model) {
			this.model = model;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getRegistrationNumber() {
			return registrationNumber;
		}

		public void setRegistrationNumber(String registrationNumber) {
			this.registrationNumber = registrationNumber;
		}

		public VehicleStatus getStatus() {
			return status;
		}

		public void setStatus(VehicleStatus status) {
			this.status = status;
		}

	public Integer getDriverId() {
		return driverId;
	}

	public void setDriverId(Integer driverId) {
		this.driverId = driverId;
	}

	public List<BookingDTO> getBookings() {
		return bookings;
	}

	public void setBookings(List<BookingDTO> bookings) {
		this.bookings = bookings;
	}
}
