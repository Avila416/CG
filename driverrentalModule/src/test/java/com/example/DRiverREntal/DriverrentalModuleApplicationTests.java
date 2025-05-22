//package com.example.DRiverREntal;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
//@SpringBootTest
//class DriverrentalModuleApplicationTests {
//
//	@Test
//	void contextLoads() {
//	}
//
//}
package com.example.DRiverREntal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.example.DRiverREntal.dto.DriverDTO;
import com.example.DRiverREntal.entity.Driver;
import com.example.DRiverREntal.exceptions.DriverNotFoundException;
import com.example.DRiverREntal.repository.DriverRepository;
import com.example.DRiverREntal.service.DriverService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest // Annotation to start the Spring Boot context for tests
@ExtendWith(MockitoExtension.class) // Enables Mockito support in JUnit 5
class DriverrentalModuleApplicationTests {

    @Mock // Mocking the DriverRepository to simulate database operations
    private DriverRepository driverRepository;

    @InjectMocks // Injecting the mocked repository into the service
    private DriverService driverService;

    private Driver driver; // Driver entity for testing
    private DriverDTO driverDTO;// DriverDTO object for testing
 // Setup method to initialize test data before each test case
    @BeforeEach
    void setUp() {
        driver = new Driver(1, "John", "Doe", "123 Main St", "1234567890", "john.doe@gmail.com", "LIC12345");
        driverDTO = new DriverDTO(1, "John", "Doe", "123 Main St", "1234567890", "john.doe@gmail.com", "LIC12345");
    }
    // Test to ensure the application context loads successfully
    @Test
    void contextLoads() {
        assertThat(driverService).isNotNull(); // Verify that driverService is initialized
    }
    // Test to verify fetching all drivers
    @Test
    void testGetAllDrivers() {
        when(driverRepository.findAll()).thenReturn(Stream.of(driver).collect(Collectors.toList()));

        List<DriverDTO> drivers = driverService.getAllDrivers();

        assertThat(drivers).isNotEmpty();// Ensure the list is not empty
        assertThat(drivers.size()).isEqualTo(1);// Verify the number of drivers
        assertThat(drivers.get(0).getFirstName()).isEqualTo("John"); // Check the driver's first name

        verify(driverRepository, times(1)).findAll();// Ensure findAll() is called once
    }
 // Test to verify fetching a driver by ID (success case)
    @Test
    void testGetDriverById_Success() {
        when(driverRepository.findById(1)).thenReturn(Optional.of(driver));

        DriverDTO foundDriver = driverService.getDriverById(1);

        assertThat(foundDriver).isNotNull();// Ensure the driver is found
        assertThat(foundDriver.getDriverId()).isEqualTo(driver.getDriverId()); // Check the driver ID
    }
 // Test to verify fetching a driver by ID (not found case)
    @Test
    void testGetDriverById_NotFound() {
        when(driverRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(DriverNotFoundException.class, () -> driverService.getDriverById(1));// Expect exception
    }
    // Test to verify creating a new driver
    @Test
    void testCreateDriver() {
        when(driverRepository.save(any(Driver.class))).thenReturn(driver);

        DriverDTO savedDriver = driverService.createDriver(driverDTO);

        assertThat(savedDriver).isNotNull();// Ensure the driver is created
        assertThat(savedDriver.getEmailId()).isEqualTo(driverDTO.getEmailId());// Verify email

        verify(driverRepository, times(1)).save(any(Driver.class));// Ensure save() is called once
    }
 // Test to verify updating an existing driver (success case)
    @Test
    void testUpdateDriver_Success() {
        when(driverRepository.findById(1)).thenReturn(Optional.of(driver));
        when(driverRepository.save(any(Driver.class))).thenReturn(driver);

        DriverDTO updatedDriver = driverService.updateDriver(1, driverDTO);

        assertThat(updatedDriver).isNotNull(); // Ensure the driver is updated
        assertThat(updatedDriver.getFirstName()).isEqualTo(driverDTO.getFirstName());// Verify updated name
    }
    // Test to verify updating a driver (not found case)
    @Test
    void testUpdateDriver_NotFound() {
        when(driverRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(DriverNotFoundException.class, () -> driverService.updateDriver(1, driverDTO)); // Expect exception
    }
    // Test to verify deleting a driver (success case)
    @Test
    void testDeleteDriver_Success() {
        when(driverRepository.existsById(1)).thenReturn(true);
        doNothing().when(driverRepository).deleteById(1);

        boolean isDeleted = driverService.deleteDriver(1);

        assertThat(isDeleted).isTrue(); // Ensure the driver is deleted successfully
        verify(driverRepository, times(1)).deleteById(1);// Ensure deleteById() is called once
    }
 // Test to verify deleting a driver (not found case)
    @Test
    void testDeleteDriver_NotFound() {
        when(driverRepository.existsById(1)).thenReturn(false);

        boolean isDeleted = driverService.deleteDriver(1);

        assertThat(isDeleted).isFalse();// Ensure deletion fails for a non-existing driver
    }
    }
