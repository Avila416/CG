package com.capgemini.entity;

import java.time.LocalDate;
import java.util.Set;

import jakarta.persistence.*;


@Entity
@Table(name="bookings")
public class Booking {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int bookingId;
    
    @Column(nullable=false)
    private Long userId;
    
    @Column(nullable=false)
    private int vehicleId;
    
    @Column(nullable=false)
    private LocalDate bookingDate;
    
    @Column(nullable=false)
    private LocalDate bookedTillDate;
    
    @Column(nullable=false)
    private String bookingDescription;
    
    @Column(nullable=false)
    private Double totalCost;
    
    @Column(nullable=false)
    private Double distance;

     @Column(nullable=false)
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



