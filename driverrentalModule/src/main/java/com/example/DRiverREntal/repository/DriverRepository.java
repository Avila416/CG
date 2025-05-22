package com.example.DRiverREntal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.DRiverREntal.entity.Driver;

import java.util.List;
//Repository interface for Driver entity, extending JpaRepository for CRUD operations
public interface DriverRepository extends JpaRepository<Driver, Integer> {

    // Custom query method to find a driver by license number
    Driver findByLicenseNo(String licenseNo);

    // Custom query methods to find drivers by first or last name
    List<Driver> findByFirstName(String firstName);
    // Custom query method to find drivers by last name
    List<Driver> findByLastName(String lastName);
}
