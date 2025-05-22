package com.vms.client;


import org.springframework.cloud.openfeign.FeignClient;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.vms.DTO.VehicleDTO;
import com.vms.exception.VehicleNotFoundException;

@FeignClient(name = "Vehicle-Service")
public interface VehicleFeignClient {
//method to get the vehicle details 
    @GetMapping("/vehicles/{vehicleId}")
    VehicleDTO getVehicleById(@PathVariable("vehicleId") int vehicleId) throws VehicleNotFoundException;
}