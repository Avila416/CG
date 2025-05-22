package com.capgemini.service;

import java.time.LocalDate;
import java.util.List;

import com.capgemini.dto.BookingDTO;
import com.capgemini.dto.UserDTO;
import com.capgemini.dto.VehicleDTO;
import com.capgemini.entity.Booking;

public interface IBookingService {

    public Booking addBooking(BookingDTO bookingDTO); // Add a booking

    public Booking updateBooking(int bookingId, BookingDTO bookingDTO); // Update a booking

    public void cancelBooking(int bookingId); // Cancel a booking

    public Booking viewBooking(int bookingId); // View a single booking by its ID

    public List<Booking> viewAllBookingsByVehicle(int vehicleId); // View all bookings for a specific vehicle

    public List<Booking> viewAllBookingsByDate(LocalDate bookingDate); // View all bookings on a specific date

    public BookingDTO convertToDTO(Booking booking); // Convert entity to DTO

    public Booking convertToEntity(BookingDTO bookingDTO); // Convert DTO to entity

    public VehicleDTO getVehicleById(int vehicleId); // Get Vehicle by ID

    public UserDTO getUserById(Long userId);//Get User by ID
}
