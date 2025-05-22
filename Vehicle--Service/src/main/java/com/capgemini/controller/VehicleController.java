
package com.capgemini.controller;

import com.capgemini.dto.VehicleDTO;
import com.capgemini.entity.VehicleStatus;
import com.capgemini.service.VehicleServiceImpul;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    @Autowired
    private VehicleServiceImpul vehicleService;

    @GetMapping("/{id}")
    public ResponseEntity<VehicleDTO> getVehicleById(@PathVariable int id) {
        return ResponseEntity.ok(vehicleService.getVehicleById(id));
    }

    @PostMapping("/register")
    public ResponseEntity<VehicleDTO> registerVehicle(@Valid @RequestBody VehicleDTO vehicleDTO) {
        VehicleDTO createdVehicle = vehicleService.registerVehicle(vehicleDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdVehicle);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehicleDTO> updateVehicle(@PathVariable int id, @Valid @RequestBody VehicleDTO vehicleDTO) {
        return ResponseEntity.ok(vehicleService.updateVehicle(id, vehicleDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVehicle(@PathVariable int id) {
        vehicleService.deleteVehicle(id);
        return ResponseEntity.ok("Vehicle with ID " + id + " deleted successfully");
    }

    @GetMapping("/available")
    public ResponseEntity<List<VehicleDTO>> getAvailableVehicles() {
        return ResponseEntity.ok(vehicleService.getAvailableVehicles());
    }

    @GetMapping("/allvehicles")
    public ResponseEntity<List<VehicleDTO>> getAll() {
        return ResponseEntity.ok(vehicleService.getAll());
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<VehicleDTO> updateVehicleStatus( @PathVariable int id,
                                                           @Valid @RequestBody VehicleStatusUpdateRequest request) {
    
        VehicleStatus status = VehicleStatus.valueOf(request.getStatus().toUpperCase());

        return ResponseEntity.ok(vehicleService.updateVehicleStatus(id, status));
    }

    // Request body class for updating vehicle status
    public static class VehicleStatusUpdateRequest {

        // Using @Pattern to validate status input
        @Pattern(regexp = "AVAILABLE|BOOKED|MAINTENANCE", message = "Invalid status. Valid values are AVAILABLE, BOOKED, or MAINTENANCE.")
        private String status;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}