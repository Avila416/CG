
package com.example.DRiverREntal.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.example.DRiverREntal.dto.DriverDTO;
import com.example.DRiverREntal.service.DriverService;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;

//REST Controller to handle driver-related operations
@RestController
@RequestMapping("/drivers")
@Validated
public class DriverController {
	// Logger instance for logging information
    private static final Logger logger = LoggerFactory.getLogger(DriverController.class);
 // Injecting DriverService dependency
    @Autowired
    private DriverService driverService;
    // Endpoint to get all drivers
    @GetMapping("/alldrivers")
    public ResponseEntity<List<DriverDTO>> getAllDrivers() {
        logger.info("Fetching all drivers");
        List<DriverDTO> drivers = driverService.getAllDrivers();
        logger.info("Fetched {} drivers", drivers.size());
        return new ResponseEntity<>(drivers, HttpStatus.OK);
    }
    // Endpoint to get a driver by ID
    @GetMapping("/{driverId}")
    public ResponseEntity<DriverDTO> getDriverById(@PathVariable int driverId) {
        logger.info("Fetching driver with ID: {}", driverId);
        DriverDTO driverDTO = driverService.getDriverById(driverId);
        if (driverDTO != null) {
            logger.info("Driver found: {}", driverDTO);
            return new ResponseEntity<>(driverDTO, HttpStatus.OK);
        } else {
            logger.warn("Driver with ID {} not found", driverId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    // Endpoint to create a new driver
    @PostMapping("/create")
    public ResponseEntity<DriverDTO> createDriver(@Valid @RequestBody DriverDTO driverDTO) {
        logger.info("Creating new driver: {}", driverDTO);
        DriverDTO createdDriver = driverService.createDriver(driverDTO);
        logger.info("Driver created successfully with ID: {}", createdDriver.getDriverId());
        return new ResponseEntity<>(createdDriver, HttpStatus.CREATED);
    }
    // Endpoint to update an existing driver
    @PutMapping("/{driverId}")
    public ResponseEntity<DriverDTO> updateDriver(@PathVariable int driverId,
                                                  @Valid @RequestBody DriverDTO driverDTO) {
        logger.info("Updating driver with ID: {}", driverId);
        DriverDTO updatedDriver = driverService.updateDriver(driverId, driverDTO);
        if (updatedDriver != null) {
            logger.info("Driver updated successfully: {}", updatedDriver);
            return new ResponseEntity<>(updatedDriver, HttpStatus.OK);
        } else {
            logger.warn("Driver with ID {} not found for update", driverId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    // Endpoint to delete a driver
    @DeleteMapping("/{driverId}")
    public ResponseEntity<String> deleteDriver(@PathVariable int driverId) {
        logger.info("Deleting driver with ID: {}", driverId);
        boolean isDeleted = driverService.deleteDriver(driverId);
        if (isDeleted) {
            logger.info("Driver with ID {} deleted successfully", driverId);
            return new ResponseEntity<>("Driver with ID " + driverId + " deleted successfully.", HttpStatus.NO_CONTENT);
        } else {
            logger.warn("Driver with ID {} not found for deletion", driverId);
            return new ResponseEntity<>("Driver with ID " + driverId + " not found.", HttpStatus.NOT_FOUND);
        }
    }
    // Handle validation errors globally in controller
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List<String>> handleValidationExceptions(ConstraintViolationException ex) {
        logger.error("Validation error: {}", ex.getMessage());
        List<String> errors = ex.getConstraintViolations()
                .stream()
                .map(violation -> violation.getMessage())
                .collect(Collectors.toList());
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}


















