package com.vms.DTO;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class VehicleDTO {

    private int vehicleId;  // Use Integer (nullable) for vehicleId

    private String brand;

    private String model;

    private String type;

    private String registrationNumber;

    private Integer driverId;
    @Enumerated(EnumType.STRING)
    private VehicleStatus status;

  

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

	public Integer getDriverId() {
		return driverId;
	}

	public void setDriverId(Integer driverId) {
		this.driverId = driverId;
	}

	public VehicleStatus getStatus() {
		return status;
	}

	public void setStatus(VehicleStatus status) {
		this.status = status;
	}

	

	
 
    // Getters and setters for all fields

   

}

