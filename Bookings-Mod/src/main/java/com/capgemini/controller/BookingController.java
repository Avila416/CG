//package com.capgemini.controller;
//
//import com.capgemini.dto.BookingDTO;
//import com.capgemini.dto.VehicleDTO;
//import com.capgemini.dto.UserDTO;
//import com.capgemini.entity.Booking;
//import com.capgemini.exception.BookingDateNotFoundException;
//import com.capgemini.exception.BookingNotFoundException;
//import com.capgemini.exception.UserNotFoundException;
//import com.capgemini.exception.VehicleNotFoundException;
//import com.capgemini.service.IBookingService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import jakarta.validation.Valid;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import java.time.LocalDate;
//import java.time.format.DateTimeParseException;
//import java.util.List;
//
//@RestController
//@RequestMapping("/bookings")
//public class BookingController {
//
//    private static final Logger logger = LoggerFactory.getLogger(BookingController.class);
//
//    @Autowired
//    private IBookingService bookingService;
//
//    // Endpoint to add a new booking
//    @PostMapping("/register")
//    public ResponseEntity<?> addBooking(@Valid @RequestBody BookingDTO bookingDTO) {
//        try {
//            logger.info("Request received to add booking: {}", bookingDTO);
//            Booking booking = bookingService.addBooking(bookingDTO);
//            logger.info("Booking added successfully: {}", booking);
//            return new ResponseEntity<>(booking, HttpStatus.CREATED);
//        } catch (UserNotFoundException | VehicleNotFoundException e) {
//            logger.error("Error adding booking: {}", e.getMessage(), e);
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
//        } catch (Exception e) {
//            logger.error("Unexpected error adding booking: {}", e.getMessage(), e);
//            return new ResponseEntity<>("Unexpected error occurred", HttpStatus.BAD_REQUEST);
//        }
//    }
//
//
//    @PutMapping("/update/{bookingId}")
//    public ResponseEntity<?> updateBooking(@PathVariable int bookingId, @RequestBody BookingDTO bookingDTO) {
//        try {
//            logger.info("Request received to update booking with ID: {}", bookingId);
//            Booking updatedBooking = bookingService.updateBooking(bookingId, bookingDTO);
//            logger.info("Booking updated successfully: {}", updatedBooking);
//            return new ResponseEntity<>(updatedBooking, HttpStatus.OK);
//        } catch (BookingNotFoundException e) {
//            logger.error("Booking not found with ID: {}", bookingId, e);
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
//        } catch (UserNotFoundException | VehicleNotFoundException e) {
//            logger.error("Error updating booking: {}", e.getMessage(), e);
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
//        } catch (Exception e) {
//            logger.error("Unexpected error updating booking: {}", e.getMessage(), e);
//            return new ResponseEntity<>("Unexpected error occurred", HttpStatus.BAD_REQUEST);
//        }
//    }
//
//
//    // Endpoint to cancel a booking
//    @DeleteMapping("/cancel/{bookingId}")
//    public ResponseEntity<String> cancelBooking(@PathVariable int bookingId) {
//        try {
//            logger.info("Request received to cancel booking with ID: {}", bookingId);
//            bookingService.cancelBooking(bookingId);
//            logger.info("Booking cancelled successfully with ID: {}", bookingId);
//            return new ResponseEntity<>("Booking cancelled successfully", HttpStatus.OK);
//        } catch (BookingNotFoundException e) {
//            logger.error("Booking not found for cancellation with ID: {}", bookingId, e);
//            return new ResponseEntity<>("Booking cancellation failed: " + e.getMessage(), HttpStatus.BAD_REQUEST);
//        }
//    }
//
//    // Endpoint to view a single booking by its ID
//    @GetMapping("/fetch/{bookingId}")
//    public ResponseEntity<?> viewBooking(@PathVariable int bookingId) {
//        try {
//            logger.info("Request received to view booking with ID: {}", bookingId);
//            Booking booking = bookingService.viewBooking(bookingId);
//            logger.info("Booking found: {}", booking);
//            return new ResponseEntity<>(booking, HttpStatus.OK);
//        } catch (BookingNotFoundException e) {
//            logger.error("Booking not found with ID: {}", bookingId, e);
//            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
//        }
//    }
//
//    // Endpoint to view all bookings for a specific vehicle
//    @GetMapping("/vehicle/{vehicleId}")
//    public ResponseEntity<?> viewAllBookingsByVehicle(@PathVariable int vehicleId) {
//        try {
//            logger.info("Request received to view all bookings for vehicle ID: {}", vehicleId);
//            List<Booking> bookings = bookingService.viewAllBookingsByVehicle(vehicleId);
//            logger.info("Bookings found for vehicle ID {}: {}", vehicleId, bookings.size());
//            return new ResponseEntity<>(bookings, HttpStatus.OK);
//        } catch (VehicleNotFoundException e) {
//            logger.error("No bookings found for vehicle ID: {}", vehicleId, e);
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
//        }
//    }
//
//    // Endpoint to view all bookings on a specific date
//    @GetMapping("/date/{bookingDate}")
//    public ResponseEntity<?> viewAllBookingsByDate(@PathVariable String bookingDate) {
//        try {
//            LocalDate date = LocalDate.parse(bookingDate);
//            logger.info("Request received to view all bookings for date: {}", date);
//            List<Booking> bookings = bookingService.viewAllBookingsByDate(date);
//            logger.info("Bookings found for date {}: {}", date, bookings.size());
//            return new ResponseEntity<>(bookings, HttpStatus.OK);
//        } catch (BookingDateNotFoundException e) {
//            logger.error("No bookings found for date: {}", bookingDate, e);
//            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
//        } catch (DateTimeParseException e) {
//            logger.error("Invalid date format for: {}", bookingDate, e);
//            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
//        }
//    }
//
//    // Endpoint to fetch user details with associated bookings
//    @GetMapping("/user/{userId}")
//    public ResponseEntity<UserDTO> getUserById(@PathVariable Long userId) {
//        try {
//            logger.info("Request received to fetch user details for user ID: {}", userId);
//            UserDTO user = bookingService.getUserById(userId);
//            logger.info("User found: {}", user);
//            return new ResponseEntity<>(user, HttpStatus.OK);
//        } catch (UserNotFoundException e) {
//            logger.error("User not found with ID: {}", userId, e);
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }
//
//    // Feign client method to fetch vehicle details from an external service
//    @GetMapping("/vehicle/details/{vehicleId}")
//    public ResponseEntity<VehicleDTO> getVehicleById(@PathVariable int vehicleId) {
//        try {
//            logger.info("Request received to fetch vehicle details for vehicle ID: {}", vehicleId);
//            VehicleDTO vehicle = bookingService.getVehicleById(vehicleId);
//            logger.info("Vehicle details found: {}", vehicle);
//            return new ResponseEntity<>(vehicle, HttpStatus.OK);
//        } catch (VehicleNotFoundException e) {
//            logger.error("Vehicle not found with ID: {}", vehicleId, e);
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }
//}
//
//












package com.capgemini.controller;

import com.capgemini.dto.BookingDTO;
import com.capgemini.dto.VehicleDTO;
import com.capgemini.dto.UserDTO;
import com.capgemini.entity.Booking;
import com.capgemini.exception.BookingDateNotFoundException;
import com.capgemini.exception.BookingNotFoundException;
import com.capgemini.exception.UserNotFoundException;
import com.capgemini.exception.VehicleNotFoundException;
import com.capgemini.service.IBookingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private static final Logger logger = LoggerFactory.getLogger(BookingController.class);

    @Autowired
    private IBookingService bookingService;

    // Endpoint to add a new booking
    @PostMapping("/register")
    public ResponseEntity<?> addBooking(@Valid @RequestBody BookingDTO bookingDTO) {
        try {
            logger.info("Request received to add booking: {}", bookingDTO);
            Booking booking = bookingService.addBooking(bookingDTO);
            logger.info("Booking added successfully: {}", booking);
            return new ResponseEntity<>(booking, HttpStatus.CREATED);
        } catch (UserNotFoundException | VehicleNotFoundException e) {
            logger.error("Error adding booking: {}", e.getMessage(), e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            logger.error("Error occurred while processing payment: {}", e.getMessage(), e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @PutMapping("/update/{bookingId}")
    public ResponseEntity<?> updateBooking(@PathVariable int bookingId, @Valid @RequestBody BookingDTO bookingDTO) {
        try {
            logger.info("Request received to update booking with ID: {}", bookingId);
            Booking updatedBooking = bookingService.updateBooking(bookingId, bookingDTO);
            logger.info("Booking updated successfully: {}", updatedBooking);
            return new ResponseEntity<>(updatedBooking, HttpStatus.OK);
        } catch (BookingNotFoundException e) {
            logger.error("Booking not found with ID: {}", bookingId, e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (UserNotFoundException | VehicleNotFoundException e) {
            logger.error("Error updating booking: {}", e.getMessage(), e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Unexpected error updating booking: {}", e.getMessage(), e);
            return new ResponseEntity<>("Unexpected error occurred", HttpStatus.NOT_FOUND);
        }
    }


    // Endpoint to cancel a booking
    @DeleteMapping("/cancel/{bookingId}")
    public ResponseEntity<String> cancelBooking(@PathVariable int bookingId) {
        try {
            logger.info("Request received to cancel booking with ID: {}", bookingId);
            bookingService.cancelBooking(bookingId);
            logger.info("Booking cancelled successfully with ID: {}", bookingId);
            return new ResponseEntity<>("Booking cancelled successfully", HttpStatus.OK);
        } catch (BookingNotFoundException e) {
            logger.error("Booking not found for cancellation with ID: {}", bookingId, e);
            return new ResponseEntity<>("Booking cancellation failed: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Endpoint to view a single booking by its ID
    @GetMapping("/fetch/{bookingId}")
    public ResponseEntity<?> viewBooking(@PathVariable int bookingId) {
        try {
            logger.info("Request received to view booking with ID: {}", bookingId);
            Booking booking = bookingService.viewBooking(bookingId);
            logger.info("Booking found: {}", booking);
            return new ResponseEntity<>(booking, HttpStatus.OK);
        } catch (BookingNotFoundException e) {
            logger.error("Booking not found with ID: {}", bookingId, e);
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    // Endpoint to view all bookings for a specific vehicle
    @GetMapping("/vehicle/{vehicleId}")
    public ResponseEntity<?> viewAllBookingsByVehicle(@PathVariable int vehicleId) {
        try {
            logger.info("Request received to view all bookings for vehicle ID: {}", vehicleId);
            List<Booking> bookings = bookingService.viewAllBookingsByVehicle(vehicleId);
            logger.info("Bookings found for vehicle ID {}: {}", vehicleId, bookings.size());
            return new ResponseEntity<>(bookings, HttpStatus.OK);
        } catch (VehicleNotFoundException e) {
            logger.error("No bookings found for vehicle ID: {}", vehicleId, e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Endpoint to view all bookings on a specific date
    @GetMapping("/date/{bookingDate}")
    public ResponseEntity<?> viewAllBookingsByDate(@PathVariable String bookingDate) {
        try {
            LocalDate date = LocalDate.parse(bookingDate);
            logger.info("Request received to view all bookings for date: {}", date);
            List<Booking> bookings = bookingService.viewAllBookingsByDate(date);
            logger.info("Bookings found for date {}: {}", date, bookings.size());
            return new ResponseEntity<>(bookings, HttpStatus.OK);
        } catch (BookingDateNotFoundException e) {
            logger.error("No bookings found for date: {}", bookingDate, e);
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        } catch (DateTimeParseException e) {
            logger.error("Invalid date format for: {}", bookingDate, e);
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    // Endpoint to fetch user details with associated bookings
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable Long userId) {
        try {
            logger.info("Request received to fetch user details for user ID: {}", userId);
            UserDTO user = bookingService.getUserById(userId);
            logger.info("User found: {}", user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            logger.error("User not found with ID: {}", userId, e);
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    // Feign client method to fetch vehicle details from an external service
    @GetMapping("/vehicle/details/{vehicleId}")
    public ResponseEntity<?> getVehicleById(@PathVariable int vehicleId) {
        try {
            logger.info("Request received to fetch vehicle details for vehicle ID: {}", vehicleId);
            VehicleDTO vehicle = bookingService.getVehicleById(vehicleId);
            logger.info("Vehicle details found: {}", vehicle);
            return new ResponseEntity<>(vehicle, HttpStatus.OK);
        } catch (VehicleNotFoundException e) {
            logger.error("Vehicle not found with ID: {}", vehicleId, e);
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }


}