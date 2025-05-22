package com.example.DRiverREntal.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.DRiverREntal.dto.DriverDTO;
import com.example.DRiverREntal.entity.Driver;
import com.example.DRiverREntal.exceptions.DriverNotFoundException;
import com.example.DRiverREntal.repository.DriverRepository;

@Service  // Marks this class as a Spring service component
public class DriverService {
	// Logger for logging messages and errors
    private static final Logger logger = LoggerFactory.getLogger(DriverService.class);

    @Autowired  // Injects the DriverRepository dependency
    private DriverRepository driverRepository;

    // Get all drivers
    public List<DriverDTO> getAllDrivers() {
        logger.info("Fetching all drivers");
        // Retrieve all drivers from the repository and convert them to DTOs
        List<DriverDTO> drivers = driverRepository.findAll()
                .stream()
                .map(this::convertToDTO)// Convert each entity to DTO
                .collect(Collectors.toList());
        logger.info("Total drivers fetched: {}", drivers.size());
        return drivers;
    }

    // Get a driver by ID
    public DriverDTO getDriverById(int driverId) {
        logger.info("Fetching driver with ID: {}", driverId);
     // Find driver by ID, throw exception if not found   
        DriverDTO driverDTO = driverRepository.findById(driverId)
                .map(this::convertToDTO) // Convert entity to DTO if present
                .orElseThrow(() -> {
                    logger.error("Driver not found with ID: {}", driverId);
                    return new DriverNotFoundException("Driver not found with ID:" + driverId);
                });
        logger.info("Driver found: {}", driverDTO);
        return driverDTO;
    }

    // Create a new driver
    public DriverDTO createDriver(DriverDTO driverDTO) {
        logger.info("Creating new driver: {}", driverDTO);
        // Convert DTO to entity and save it to the database
        Driver driver = convertToEntity(driverDTO);
        driver = driverRepository.save(driver);// Save driver
        DriverDTO createdDriver = convertToDTO(driver); // Convert saved entity back to DTO
        logger.info("Driver created successfully with ID: {}", createdDriver.getDriverId());
        return createdDriver;
    }

    // Update an existing driver
    public DriverDTO updateDriver(int driverId, DriverDTO driverDTO) {
        logger.info("Updating driver with ID: {}", driverId);
     // Check if driver exists, throw exception if not found
        Driver existingDriver = driverRepository.findById(driverId)
                .orElseThrow(() -> {
                    logger.error("Driver not found with ID: {}", driverId);
                    return new DriverNotFoundException("Driver not found with ID: " + driverId);
                });

        // Update driver fields
        existingDriver.setFirstName(driverDTO.getFirstName());
        existingDriver.setLastName(driverDTO.getLastName());
        existingDriver.setAddress(driverDTO.getAddress());
        existingDriver.setMobileNumber(driverDTO.getMobileNumber());
        existingDriver.setEmailId(driverDTO.getEmailId());
        existingDriver.setLicenseNo(driverDTO.getLicenseNo());
        // Save the updated driver entity
        Driver updatedDriver = driverRepository.save(existingDriver);
        logger.info("Driver updated successfully with ID: {}", updatedDriver.getDriverId());
     // Return the updated driver as a DTO
        return convertToDTO(updatedDriver);
    }

    // Delete a driver
    public boolean deleteDriver(int driverId) {
        logger.info("Deleting driver with ID: {}", driverId);
     // Check if driver exists before deleting
        if (driverRepository.existsById(driverId)) {
            driverRepository.deleteById(driverId);// Delete driver
            logger.info("Driver deleted successfully with ID: {}", driverId);
            return true; // Return true if deleted successfully
        } else {
            logger.warn("Attempted to delete non-existing driver with ID: {}", driverId);
            return false;// Return false if driver not found
        }
    }

    // Convert entity to DTO
    private DriverDTO convertToDTO(Driver driver) {
        return new DriverDTO(
                driver.getDriverId(),
                driver.getFirstName(),
                driver.getLastName(),
                driver.getAddress(),
                driver.getMobileNumber(),
                driver.getEmailId(),
                driver.getLicenseNo()
        );
    }

    // Convert DTO to entity
    private Driver convertToEntity(DriverDTO driverDTO) {
        return new Driver(
                driverDTO.getDriverId(),
                driverDTO.getFirstName(),
                driverDTO.getLastName(),
                driverDTO.getAddress(),
                driverDTO.getMobileNumber(),
                driverDTO.getEmailId(),
                driverDTO.getLicenseNo()
        );
    }
}
