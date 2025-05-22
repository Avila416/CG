package com.capgemini.dto;

import com.capgemini.entity.VehicleStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public class VehicleDTO {

	private int vehicleId;

	@NotBlank(message = "{vehicle.brand.required}")
	private String brand;

	@NotBlank(message = "{vehicle.model.required}")
	private String model;

	@NotBlank(message = "{vehicle.type.required}")
	private String type;

	@NotBlank(message = "{vehicle.registration.required}")
	@Size(min = 10, max = 10, message = "{vehicle.registration.size}")
	@Pattern(regexp = "^[A-Z]{2}\\d{2}[A-Z]{2}\\d{4}$", message = "{vehicle.registration.pattern}")
	private String registrationNumber;

	@PositiveOrZero(message = "{vehicle.driverId.positive}")
	@NotNull(message = "{vehicle.driverId.required}")
	private Integer driverId;

	@NotNull(message = "{vehicle.status.required}")
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

	public VehicleDTO(int vehicleId,
			@NotBlank(message = "{vehicle.brand.required}") @NotNull(message = "{vehicle.brand.required}") String brand,
			@NotBlank(message = "{vehicle.model.required}") @NotNull(message = "{vehicle.model.required}") String model,
			@NotBlank(message = "{vehicle.type.required}") @NotNull(message = "{vehicle.type.required}") String type,
			@NotNull(message = "{vehicle.registration.required}") @Size(min = 10, max = 10, message = "{vehicle.registration.size}") @Pattern(regexp = "^[A-Z]{2}\\d{2}[A-Z]{2}\\d{4}$", message = "{vehicle.registration.pattern}") String registrationNumber,
			@PositiveOrZero(message = "{vehicle.driverId.positive}") @NotNull(message = "{vehicle.driverId.required}") Integer driverId,
			@NotNull(message = "{vehicle.status.required}") VehicleStatus status) {
		super();
		this.vehicleId = vehicleId;
		this.brand = brand;
		this.model = model;
		this.type = type;
		this.registrationNumber = registrationNumber;
		this.driverId = driverId;
		this.status = status;
	}

	public VehicleDTO() {
		super();
	}

}
