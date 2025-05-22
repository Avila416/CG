package com.vms.DTO;

import java.time.LocalDate;

/**
 * Data Transfer Object (DTO) for Booking details.
 */
public class BookingDTO {

    private int bookingId; // Unique identifier for the booking
    private Long userId; // ID of the user who made the booking
    private int vehicleId; // ID of the booked vehicle
    private LocalDate bookingDate; // Date when the booking was made
    private LocalDate bookedTillDate; // Date until the vehicle is booked
    private String bookingDescription; // Description or remarks about the booking
    private double totalCost; // Total cost of the booking
    private double distance; // Estimated or actual distance traveled

    // Getter and Setter for bookingId
    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    // Getter and Setter for userId
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    // Getter and Setter for vehicleId
    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    // Getter and Setter for bookingDate
    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    // Getter and Setter for bookedTillDate
    public LocalDate getBookedTillDate() {
        return bookedTillDate;
    }

    public void setBookedTillDate(LocalDate bookedTillDate) {
        this.bookedTillDate = bookedTillDate;
    }

    // Getter and Setter for bookingDescription
    public String getBookingDescription() {
        return bookingDescription;
    }

    public void setBookingDescription(String bookingDescription) {
        this.bookingDescription = bookingDescription;
    }

    // Getter and Setter for totalCost
    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    // Getter and Setter for distance
    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}

 