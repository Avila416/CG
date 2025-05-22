package com.capgemini;

import com.capgemini.exception.BookingDateNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.capgemini.controller.BookingController;
import com.capgemini.dto.BookingDTO;
import com.capgemini.dto.VehicleDTO;
import com.capgemini.dto.UserDTO;
import com.capgemini.entity.Booking;
import com.capgemini.exception.BookingNotFoundException;
import com.capgemini.exception.UserNotFoundException;
import com.capgemini.exception.VehicleNotFoundException;
import com.capgemini.service.IBookingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookingsModApplicationTests {

//	@Test
//	void contextLoads() {
//	}

	@Mock
	private IBookingService bookingService;

	@InjectMocks
	private BookingController bookingController;

	private BookingDTO bookingDTO;
	private Booking booking;

	@BeforeEach
	void setUp() {


		MockitoAnnotations.openMocks(this);
		// Initialize BookingDTO and Booking objects for testing
		bookingDTO = new BookingDTO();
		bookingDTO.setUserId(1L);
		bookingDTO.setVehicleId(1);
		bookingDTO.setBookingDate(LocalDate.now());
		bookingDTO.setBookedTillDate(LocalDate.now().plusDays(2));
		bookingDTO.setBookingDescription("Test Booking");
		bookingDTO.setTotalCost(250.0);
		bookingDTO.setDistance(100.5);
		bookingDTO.setDeliveryType("CUSTOMER_PICKUP");

		booking = new Booking();
		booking.setBookingId(1);
		booking.setUserId(1L);
		booking.setVehicleId(1);
		booking.setBookingDate(LocalDate.now());
		booking.setBookedTillDate(LocalDate.now().plusDays(2));
		booking.setBookingDescription("Test Booking");
		booking.setTotalCost(250.0);
		booking.setDistance(100.5);
		booking.setDeliveryType("CUSTOMER_PICKUP");
	}

	// Test case for adding a booking
	@Test
	void testAddBooking() {
		// Mock the service method
		when(bookingService.addBooking(any(BookingDTO.class))).thenReturn(booking);

		// Call the controller method
		ResponseEntity<?> response = bookingController.addBooking(bookingDTO);

		// Assertions
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(booking, response.getBody());
	}


	// Test case for updating a booking
	@Test
	void testUpdateBooking() {
		// Mock the service method
		when(bookingService.updateBooking(anyInt(), any(BookingDTO.class))).thenReturn(booking);

		// Call the controller method
		ResponseEntity<?> response = bookingController.updateBooking(1, bookingDTO);

		// Assertions
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(booking, response.getBody());
	}

	// Test case for updating a booking when booking is not found
	@Test
	void testUpdateBooking_NotFound() {
		// Mock the service method to throw BookingNotFoundException
		when(bookingService.updateBooking(anyInt(), any(BookingDTO.class)))
				.thenThrow(new BookingNotFoundException("Booking not found"));

		// Call the controller method
		ResponseEntity<?> response = bookingController.updateBooking(1, bookingDTO);

		// Assertions
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals("Booking not found", response.getBody());
	}

	// Test case for adding a booking with UserNotFoundException
	@Test
	void testAddBooking_UserNotFound() {
		// Mock the service method to throw UserNotFoundException
		when(bookingService.addBooking(any(BookingDTO.class)))
				.thenThrow(new UserNotFoundException("User not found"));

		// Call the controller method
		ResponseEntity<?> response = bookingController.addBooking(bookingDTO);

		// Assertions
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals("User not found", response.getBody());
	}

	@Test
	void testCancelBooking() throws BookingNotFoundException {
		// Mock the service method
		doNothing().when(bookingService).cancelBooking(anyInt());

		// Call the controller method
		ResponseEntity<String> response = bookingController.cancelBooking(1);

		// Assertions
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("Booking cancelled successfully", response.getBody());
	}

	@Test
	void testCancelBooking_NotFound() throws BookingNotFoundException {
		// Mock the service method
		doThrow(new BookingNotFoundException("Booking not found")).when(bookingService).cancelBooking(anyInt());

		// Call the controller method
		ResponseEntity<String> response = bookingController.cancelBooking(1);

		// Assertions
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}

	@Test
	void testViewBooking_NotFound() throws BookingNotFoundException {
		// Mock the service method
		when(bookingService.viewBooking(anyInt())).thenThrow(new BookingNotFoundException("Booking not found"));

		// Call the controller method
		ResponseEntity<?> response = bookingController.viewBooking(1);

		// Assertions
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}


	@Test
	void testGetUserById() throws UserNotFoundException {
		// Mock the service method
		UserDTO userDTO = new UserDTO();
		userDTO.setUserId(1L);
		userDTO.setUsername("testuser");
		when(bookingService.getUserById(anyLong())).thenReturn(userDTO);

		// Call the controller method
		ResponseEntity<UserDTO> response = bookingController.getUserById(1L);

		// Assertions
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(userDTO, response.getBody());
	}


	@Test
	void testGetVehicleById() throws VehicleNotFoundException {
		// Mock the service method
		VehicleDTO vehicleDTO = new VehicleDTO();
		vehicleDTO.setVehicleId(1);
		vehicleDTO.setBrand("Toyota");
		vehicleDTO.setModel("Camry");
		when(bookingService.getVehicleById(anyInt())).thenReturn(vehicleDTO);

		// Call the controller method
		ResponseEntity<VehicleDTO> response = bookingController.getVehicleById(1);

		// Assertions
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(vehicleDTO, response.getBody());
	}

}