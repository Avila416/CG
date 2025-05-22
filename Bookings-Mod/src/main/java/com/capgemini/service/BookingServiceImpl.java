package com.capgemini.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.capgemini.client.UserClient;
import com.capgemini.dto.UserDTO;
import com.capgemini.exception.BookingDateNotFoundException;
import com.capgemini.exception.UserNotFoundException;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.client.VehicleClient;
import com.capgemini.dto.BookingDTO;
import com.capgemini.dto.VehicleDTO;
import com.capgemini.entity.Booking;
import com.capgemini.exception.BookingNotFoundException;
import com.capgemini.exception.VehicleNotFoundException;
import com.capgemini.repository.BookingRepo;

@Service
public class BookingServiceImpl implements IBookingService {

    // Logger initialization using SLF4J
    private static final Logger logger = LoggerFactory.getLogger(BookingServiceImpl.class);

    @Autowired
    private BookingRepo bookingRepo;  // Repository for Booking entity

    @Autowired
    private VehicleClient vehicleClient;  // Feign client for Vehicle service

    @Autowired
    private UserClient userClient; // Feign client for user service

    @Override
    public Booking addBooking(BookingDTO bookingDTO) {
        logger.info("Adding booking with details: {}", bookingDTO);

        // Fetch vehicle details using Feign Client
        VehicleDTO vehicle;
        try {
            vehicle = vehicleClient.getVehicleById(bookingDTO.getVehicleId());
        } catch (FeignException.NotFound e) {
            logger.error("Vehicle not found with ID: {}", bookingDTO.getVehicleId());
            throw new VehicleNotFoundException("Vehicle not found with ID: " + bookingDTO.getVehicleId());
        }

        // Fetch user details using Feign Client (UserClient)
        UserDTO user;
        try {
            user = userClient.getUserById((long) bookingDTO.getUserId());
        } catch (FeignException.NotFound e) {
            logger.error("User not found with ID: {}", bookingDTO.getUserId());
            throw new UserNotFoundException("User not found with ID: " + bookingDTO.getUserId());
        }

        // Convert BookingDTO to Booking entity and save
        Booking booking = convertToEntity(bookingDTO);
        Booking savedBooking = bookingRepo.save(booking);
        logger.info("Booking added with ID: {}", savedBooking.getBookingId());

        return savedBooking;
    }


    @Override
    public Booking updateBooking(int bookingId, BookingDTO bookingDTO) {
        logger.info("Updating booking with ID: {}", bookingId);

        // Check if the booking exists
        Booking existingBooking = bookingRepo.findById(bookingId)
                .orElseThrow(() -> {
                    logger.error("Booking not found with ID: {}", bookingId);
                    return new BookingNotFoundException("Booking not found with ID: " + bookingId);
                });

        // Fetch vehicle details using Feign Client
        VehicleDTO vehicle;
        try {
            vehicle = vehicleClient.getVehicleById(bookingDTO.getVehicleId());
        } catch (VehicleNotFoundException e) {
            logger.error("Vehicle not found with ID: {}", bookingDTO.getVehicleId());
            throw new VehicleNotFoundException("Vehicle not found with ID: " + bookingDTO.getVehicleId());
        }

        // Fetch user details using Feign Client (UserClient)
        UserDTO user;
        try {
            user = userClient.getUserById(bookingDTO.getUserId());
        } catch (UserNotFoundException e) {
            logger.error("User not found with ID: {}", bookingDTO.getUserId());
            throw new UserNotFoundException("User not found with ID: " + bookingDTO.getUserId());
        }

        // Update fields of the existing booking
        existingBooking.setUserId(bookingDTO.getUserId());
        existingBooking.setVehicleId(bookingDTO.getVehicleId());
        existingBooking.setBookingDate(bookingDTO.getBookingDate());
        existingBooking.setBookedTillDate(bookingDTO.getBookedTillDate());
        existingBooking.setBookingDescription(bookingDTO.getBookingDescription());
        existingBooking.setTotalCost(bookingDTO.getTotalCost());
        existingBooking.setDistance(bookingDTO.getDistance());
        existingBooking.setDeliveryType(bookingDTO.getDeliveryType());

        Booking updatedBooking = bookingRepo.save(existingBooking);
        logger.info("Booking updated with ID: {}", updatedBooking.getBookingId());
        return updatedBooking;
    }


    @Override
    public void cancelBooking(int bookingId) {
        logger.info("Canceling booking with ID: {}", bookingId);

        Booking booking = bookingRepo.findById(bookingId)
                .orElseThrow(() -> {
                    logger.error("Booking not found with ID: {}", bookingId);
                    return new BookingNotFoundException("Booking not found with ID: " + bookingId);
                });

        bookingRepo.delete(booking);  // Delete the booking
        logger.info("Booking with ID: {} cancelled successfully", bookingId);
    }



    @Override
    public Booking viewBooking(int bookingId) {
        logger.info("Fetching booking with ID: {}", bookingId);

        Booking booking = bookingRepo.findById(bookingId)
                .orElseThrow(() -> {
                    logger.error("Booking not found with ID: {}", bookingId);
                    return new BookingNotFoundException("Booking not found with ID: " + bookingId);
                });

        logger.info("Booking details retrieved for ID: {}", bookingId);
        return booking;
    }

    @Override
    public List<Booking> viewAllBookingsByVehicle(int vehicleId) {
        logger.info("Fetching all bookings for vehicle with ID: {}", vehicleId);

        List<Booking> bookings = bookingRepo.findByVehicleId(vehicleId);
        if (bookings.isEmpty()) {
            logger.error("No bookings found for vehicle with ID: {}", vehicleId);
            throw new VehicleNotFoundException("No bookings found for vehicle with ID: " + vehicleId);
        }

        logger.info("Retrieved {} bookings for vehicle with ID: {}", bookings.size(), vehicleId);
        return bookings;
    }

    @Override
    public List<Booking> viewAllBookingsByDate(LocalDate bookingDate) {
        logger.info("Fetching all bookings for the date: {}", bookingDate);

        List<Booking> bookings = bookingRepo.findByBookingDate(bookingDate);
        if (bookings.isEmpty()) {
            logger.error("No bookings found for the date: {}", bookingDate);
            throw new BookingDateNotFoundException("No bookings found for the date: " + bookingDate);
        }

        logger.info("Retrieved {} bookings for the date: {}", bookings.size(), bookingDate);
        return bookings;
    }

    @Override
    public VehicleDTO getVehicleById(int vehicleId) {
        logger.info("Fetching vehicle details for vehicle ID: {}", vehicleId);

        // Fetch vehicle details using the Feign Client
        VehicleDTO vehicle = vehicleClient.getVehicleById(vehicleId);
        if (vehicle == null) {
            logger.error("Vehicle not found with ID: {}", vehicleId);
            throw new VehicleNotFoundException("Vehicle not found with ID: " + vehicleId);
        }

        logger.info("Successfully fetched vehicle details for vehicle ID: {}", vehicleId);

        // Fetch all bookings for the vehicle by vehicleId
        List<Booking> bookings = bookingRepo.findByVehicleId(vehicleId);
        if (bookings.isEmpty()) {
            logger.warn("No bookings found for vehicle with ID: {}", vehicleId);
            throw new BookingNotFoundException("No bookings found for vehicle with ID: " + vehicleId);
        }

        logger.info("Found {} bookings for vehicle with ID: {}", bookings.size(), vehicleId);

        // Convert each Booking entity to BookingDTO
        List<BookingDTO> bookingDTOs = bookings.stream()
                .map(this::convertToDTO)
                .toList();

        // Set bookings in the vehicleDTO
        vehicle.setBookings(bookingDTOs);

        logger.info("Bookings added to the vehicleDTO for vehicle ID: {}", vehicleId);

        return vehicle;  // Return vehicle with booking details
    }

    // Method to fetch user details along with all bookings associated with userId
    @Override
    public UserDTO getUserById(Long userId) {
        logger.info("Fetching user details for user ID: {}", userId);

        // Fetch user details using Feign Client
        UserDTO user = userClient.getUserById(userId);
        if (user == null) {
            logger.error("User not found with ID: {}", userId);
            throw new UserNotFoundException("User not found with ID: " + userId);
        }

        // Fetch all bookings for the user from the database
        List<Booking> bookings = bookingRepo.findByUserId(userId);
        if (bookings.isEmpty()) {
            logger.error("No bookings found for user with ID: {}", userId);
            throw new BookingNotFoundException("No bookings found for user with ID: " + userId);
        }

        // Convert each Booking entity to BookingDTO and add to user's bookings
        List<BookingDTO> bookingDTOs = bookings.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        // Add the booking details to the userDTO
        user.setBookings(bookingDTOs);

        logger.info("Successfully fetched user details along with {} bookings.", bookingDTOs.size());

        return user;
    }

    // Conversion method to convert BookingDTO to Booking entity
    public Booking convertToEntity(BookingDTO bookingDTO) {
        // Creating a new Booking entity object
        Booking booking = new Booking();

        // Setting properties of the Booking entity based on the BookingDTO values
        booking.setBookingId(bookingDTO.getBookingId());
        booking.setUserId(bookingDTO.getUserId());
        booking.setVehicleId(bookingDTO.getVehicleId());
        booking.setBookingDate(bookingDTO.getBookingDate());
        booking.setBookedTillDate(bookingDTO.getBookedTillDate());
        booking.setBookingDescription(bookingDTO.getBookingDescription());
        booking.setTotalCost(bookingDTO.getTotalCost());
        booking.setDistance(bookingDTO.getDistance());
        booking.setDeliveryType(bookingDTO.getDeliveryType());

        // Returning the populated Booking entity object
        return booking;
    }

    // Convert Booking entity to BookingDTO
    public BookingDTO convertToDTO(Booking booking) {
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setBookingId(booking.getBookingId());
        bookingDTO.setUserId(booking.getUserId());
        bookingDTO.setVehicleId(booking.getVehicleId());
        bookingDTO.setBookingDate(booking.getBookingDate());
        bookingDTO.setBookedTillDate(booking.getBookedTillDate());
        bookingDTO.setBookingDescription(booking.getBookingDescription());
        bookingDTO.setTotalCost(booking.getTotalCost());
        bookingDTO.setDistance(booking.getDistance());
        bookingDTO.setDeliveryType(booking.getDeliveryType());
        return bookingDTO;
    }
}