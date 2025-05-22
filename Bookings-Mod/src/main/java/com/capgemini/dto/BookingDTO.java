package com.capgemini.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.*;

public class BookingDTO {
private int bookingId;
	
	@PositiveOrZero(message="{validation.userId.positive}")
    @NotNull(message = "{validation.userId.notNull}")
	private Long userId;
	
	@NotNull(message="{validation.vehicleId.notNull}")
	@Positive(message="{validation.vehicleId.positive}")
    private int vehicleId;
	
	
	@NotNull(message = "{validation.bookingDate.notNull}")
    private LocalDate bookingDate;
	
	@NotNull(message = "{validation.bookedTillDate.notNull}")
    private LocalDate bookedTillDate;
		
	@NotBlank(message = "{validation.bookingDescription.notBlank}")
	@Size(max =  255, message = "{validation.bookingDescription.size}")
    private String bookingDescription;
	
	@NotNull(message = "{validation.totalCost.notNull}")
	@DecimalMin(value = "0.01", inclusive = true, message = "{validation.totalCost.decimalMin}")
    @DecimalMax(value = "1000000.0", inclusive = true, message = "{validation.totalCost.decimalMax}")
    private Double totalCost;
	
	@NotNull( message = "{validation.distance.notNull}")
	@Positive(message="{validation.distance.positive}")
    private Double distance;

	@NotBlank(message="{validation.deliveryType.notBlank}")
	@Pattern(regexp="^(COMPANY_DELIVERY|CUSTOMER_PICKUP)$",message="{validation.deliveryType.pattern}")
	private String deliveryType;

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public int getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}


	public LocalDate getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
	}

	public LocalDate getBookedTillDate() {
		return bookedTillDate;
	}

	public void setBookedTillDate(LocalDate bookedTillDate) {
		this.bookedTillDate = bookedTillDate;
	}

	public String getBookingDescription() {
		return bookingDescription;
	}

	public void setBookingDescription(String bookingDescription) {
		this.bookingDescription = bookingDescription;
	}

	public Double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(Double totalCost) {
		this.totalCost = totalCost;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public String getDeliveryType() {
		return deliveryType;
	}

	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}
}

