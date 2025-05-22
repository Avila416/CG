
//
//package com.vms.controller;
//
//import com.vms.DTO.BookingDTO;
//import com.vms.DTO.PaymentDTO;
//import com.vms.DTO.VehicleDTO;
//import com.vms.exception.PaymentNotFoundException;
//import com.vms.service.IPaymentService;
//import com.vms.client.BookingFeignClient;
//import com.vms.client.VehicleFeignClient;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//
//import jakarta.validation.Valid;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//@RestController
//@RequestMapping("/payments")  // Base path for payment-related endpoints
//public class PaymentController {
//
//    private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);  // Logger instance
//
//    @Autowired
//    private IPaymentService paymentService;  // Service for payment-related operations
//
//    @Autowired
//    private BookingFeignClient bookingFeignClient;  // Feign client for booking service
//
//    @Autowired
//    private VehicleFeignClient vehicleFeignClient;  // Feign client for vehicle service
//
//    /**
//     * Endpoint to create a new payment.
//     */
//    @PostMapping("/create")
//    public ResponseEntity<?> addPayment(@RequestBody @Valid PaymentDTO paymentDTO, BindingResult bindingResult) {
//        logger.info("Received request to create a new payment: {}", paymentDTO);
//
//        // Validate input fields
//        if (bindingResult.hasErrors()) {
//            List<String> errors = bindingResult.getAllErrors().stream()
//                    .map(error -> error.getDefaultMessage())
//                    .collect(Collectors.toList());
//            logger.warn("Validation errors occurred: {}", errors);
//            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
//        }
//
//        try {
//            // Save payment details
//            PaymentDTO savedPayment = paymentService.addPayment(paymentDTO);
//            logger.info("Payment successfully created with ID: {}", savedPayment.getPaymentId());
//
//            // Fetch booking details from Booking Service
//            BookingDTO bookingDTO = bookingFeignClient.getBookingById(paymentDTO.getBookingId());
//            if (bookingDTO == null) {
//                logger.warn("Booking not found for ID: {}", paymentDTO.getBookingId());
//                return new ResponseEntity<>("Booking not found", HttpStatus.NOT_FOUND);
//            }
//
//            // Fetch vehicle details from Vehicle Service
//            VehicleDTO vehicleDTO = vehicleFeignClient.getVehicleById(paymentDTO.getVehicleId());
//            if (vehicleDTO == null) {
//                logger.warn("Vehicle not found for ID: {}", paymentDTO.getVehicleId());
//                return new ResponseEntity<>("Vehicle not found", HttpStatus.NOT_FOUND);
//            }
//
//            // Construct response object
//            Map<String, Object> response = new HashMap<>();
//            response.put("booking", bookingDTO);
//            response.put("payment", savedPayment);
//            response.put("vehicle", vehicleDTO);
//
//            return new ResponseEntity<>(response, HttpStatus.CREATED);  // Return created response
//        } catch (Exception e) {
//            logger.error("Error occurred while processing payment: {}", e.getMessage(), e);
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);  // Handle internal server error
//        }
//    }
//
//    /**
//     * Endpoint to cancel a payment by ID.
//     */
//    @PutMapping("/cancel/{paymentId}")
//    public ResponseEntity<String> cancelPayment(@PathVariable int paymentId) {
//        logger.info("Received request to cancel payment with ID: {}", paymentId);
//        try {
//            paymentService.cancelPayment(paymentId);  // Call service to cancel payment
//            logger.info("Payment cancelled successfully: {}", paymentId);
//            return new ResponseEntity<>("Payment cancelled successfully", HttpStatus.OK);
//        } catch (PaymentNotFoundException e) {
//            logger.warn("Payment cancellation failed: {}", e.getMessage());
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);  // Handle case where payment is not found
//        }
//    }
//
//    /**
//     * Endpoint to fetch payment details by booking ID.
//     */
//    @GetMapping("/booking/{bookingId}")
//    public ResponseEntity<?> viewPaymentByBookingId(@PathVariable int bookingId) {
//        logger.info("Fetching payment details for booking ID: {}", bookingId);
//        try {
//            // Retrieve payment details for the given booking ID
//            PaymentDTO paymentDTO = paymentService.viewPaymentByBookingId(bookingId);
//            if (paymentDTO == null) {
//                logger.warn("No payment found for booking ID: {}", bookingId);
//                return new ResponseEntity<>("No payment found", HttpStatus.NOT_FOUND);
//            }
//
//            // Fetch booking details
//            BookingDTO bookingDTO = bookingFeignClient.getBookingById(paymentDTO.getBookingId());
//            if (bookingDTO == null) {
//                logger.warn("Booking not found for ID: {}", paymentDTO.getBookingId());
//                return new ResponseEntity<>("Booking not found", HttpStatus.NOT_FOUND);
//            }
//
//            // Fetch vehicle details
//            VehicleDTO vehicleDTO = vehicleFeignClient.getVehicleById(paymentDTO.getVehicleId());
//            if (vehicleDTO == null) {
//                logger.warn("Vehicle not found for ID: {}", paymentDTO.getVehicleId());
//                return new ResponseEntity<>("Vehicle not found", HttpStatus.NOT_FOUND);
//            }
//
//            // Construct response with booking, payment, and vehicle details
//            Map<String, Object> response = new HashMap<>();
//            response.put("booking", bookingDTO);
//            response.put("payment", paymentDTO);
//            response.put("vehicle", vehicleDTO);
//
//            return new ResponseEntity<>(response, HttpStatus.OK);  // Return successful response
//        } catch (PaymentNotFoundException e) {
//            logger.warn("Payment not found for booking ID: {}", bookingId);
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);  // Handle case where payment is not found
//        }
//    }
//}
//
//
package com.vms.controller;

import com.vms.DTO.BookingDTO;
import com.vms.DTO.PaymentDTO;
import com.vms.DTO.VehicleDTO;
import com.vms.exception.PaymentNotFoundException;
import com.vms.service.IPaymentService;
import com.vms.client.BookingFeignClient;
import com.vms.client.VehicleFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);

    @Autowired
    private IPaymentService paymentService;

    @Autowired
    private BookingFeignClient bookingFeignClient;

    @Autowired
    private VehicleFeignClient vehicleFeignClient;

    // Existing methods for adding and canceling payments, as well as fetching payment details

    @PostMapping("/create")
    public ResponseEntity<?> addPayment(@RequestBody @Valid PaymentDTO paymentDTO, BindingResult bindingResult) {
        logger.info("Received request to create a new payment: {}", paymentDTO);

        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors().stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.toList());
            logger.warn("Validation errors occurred: {}", errors);
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        try {
            PaymentDTO savedPayment = paymentService.addPayment(paymentDTO);
            logger.info("Payment successfully created with ID: {}", savedPayment.getPaymentId());

            BookingDTO bookingDTO = bookingFeignClient.getBookingById(paymentDTO.getBookingId());
            if (bookingDTO == null) {
                logger.warn("Booking not found for ID: {}", paymentDTO.getBookingId());
                return new ResponseEntity<>("Booking not found", HttpStatus.NOT_FOUND);
            }

            VehicleDTO vehicleDTO = vehicleFeignClient.getVehicleById(paymentDTO.getVehicleId());
            if (vehicleDTO == null) {
                logger.warn("Vehicle not found for ID: {}", paymentDTO.getVehicleId());
                return new ResponseEntity<>("Vehicle not found", HttpStatus.NOT_FOUND);
            }

            Map<String, Object> response = new HashMap<>();
            response.put("booking", bookingDTO);
            response.put("payment", savedPayment);
            response.put("vehicle", vehicleDTO);

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error occurred while processing payment: {}", e.getMessage(), e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/cancel/{paymentId}")
    public ResponseEntity<String> cancelPayment(@PathVariable int paymentId) {
        logger.info("Received request to cancel payment with ID: {}", paymentId);
        try {
            paymentService.cancelPayment(paymentId);
            logger.info("Payment cancelled successfully: {}", paymentId);
            return new ResponseEntity<>("Payment cancelled successfully", HttpStatus.OK);
        } catch (PaymentNotFoundException e) {
            logger.warn("Payment cancellation failed: {}", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/booking/{bookingId}")
    public ResponseEntity<?> viewPaymentByBookingId(@PathVariable int bookingId) {
        logger.info("Fetching payment details for booking ID: {}", bookingId);
        try {
            PaymentDTO paymentDTO = paymentService.viewPaymentByBookingId(bookingId);
            if (paymentDTO == null) {
                logger.warn("No payment found for booking ID: {}", bookingId);
                return new ResponseEntity<>("No payment found", HttpStatus.NOT_FOUND);
            }

            BookingDTO bookingDTO = bookingFeignClient.getBookingById(paymentDTO.getBookingId());
            if (bookingDTO == null) {
                logger.warn("Booking not found for ID: {}", paymentDTO.getBookingId());
                return new ResponseEntity<>("Booking not found", HttpStatus.NOT_FOUND);
            }

            VehicleDTO vehicleDTO = vehicleFeignClient.getVehicleById(paymentDTO.getVehicleId());
            if (vehicleDTO == null) {
                logger.warn("Vehicle not found for ID: {}", paymentDTO.getVehicleId());
                return new ResponseEntity<>("Vehicle not found", HttpStatus.NOT_FOUND);
            }

            Map<String, Object> response = new HashMap<>();
            response.put("booking", bookingDTO);
            response.put("payment", paymentDTO);
            response.put("vehicle", vehicleDTO);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (PaymentNotFoundException e) {
            logger.warn("Payment not found for booking ID: {}", bookingId);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/vehicle/{vehicleId}")
    public ResponseEntity<?> viewPaymentByVehicleId(@PathVariable int vehicleId) {
        logger.info("Fetching payment details for vehicle ID: {}", vehicleId);
        try {
            PaymentDTO paymentDTO = paymentService.viewPaymentByVehicleId(vehicleId);
            if (paymentDTO == null) {
                logger.warn("No payment found for vehicle ID: {}", vehicleId);
                return new ResponseEntity<>("No payment found for vehicle ID: " + vehicleId, HttpStatus.NOT_FOUND);
            }

            BookingDTO bookingDTO = bookingFeignClient.getBookingById(paymentDTO.getBookingId());
            if (bookingDTO == null) {
                logger.warn("Booking not found for ID: {}", paymentDTO.getBookingId());
                return new ResponseEntity<>("Booking not found", HttpStatus.NOT_FOUND);
            }

            VehicleDTO vehicleDTO = vehicleFeignClient.getVehicleById(vehicleId);
            if (vehicleDTO == null) {
                logger.warn("Vehicle not found for ID: {}", vehicleId);
                return new ResponseEntity<>("Vehicle not found", HttpStatus.NOT_FOUND);
            }

            Map<String, Object> response = new HashMap<>();
            response.put("booking", bookingDTO);
            response.put("payment", paymentDTO);
            response.put("vehicle", vehicleDTO);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (PaymentNotFoundException e) {
            logger.warn("Payment not found for vehicle ID: {}", vehicleId);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // New methods for calculating revenue and total payments

    @GetMapping("/revenue")
    public ResponseEntity<?> calculateMonthlyRevenue(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        logger.info("Calculating monthly revenue from {} to {}", startDate, endDate);
        try {
            double revenue = paymentService.calculateMonthlyRevenue(startDate, endDate);
            logger.info("Calculated monthly revenue: {}", revenue);
            return new ResponseEntity<>(revenue, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error calculating revenue: {}", e.getMessage(), e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/vehicle/{vehicleId}/total")
    public ResponseEntity<?> calculateTotalPaymentForVehicle(@PathVariable int vehicleId) {
        logger.info("Calculating total payment for vehicle ID: {}", vehicleId);
        try {
            double totalPayment = paymentService.calculateTotalPaymentForVehicle(vehicleId);
            logger.info("Calculated total payment for vehicle {}: {}", vehicleId, totalPayment);
            return new ResponseEntity<>(totalPayment, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error calculating total payment for vehicle {}: {}", vehicleId, e.getMessage(), e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/vehicle/{vehicleId}/payment")
    public ResponseEntity<?> calculateTotalPayment(@PathVariable int vehicleId) {
        logger.info("Calculating total payment for vehicle ID: {}", vehicleId);
        try {
            Double totalPayment = paymentService.calculateTotalPaymentForVehicle(vehicleId);
            logger.info("Calculated total payment for vehicle ID {}: {}", vehicleId, totalPayment);
            return new ResponseEntity<>(totalPayment, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error calculating total payment for vehicle ID {}: {}", vehicleId, e.getMessage(), e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
