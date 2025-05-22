//package com.capgemini.client;
//
//import com.capgemini.dto.VehicleDTO;
//import com.capgemini.exception.VehicleNotFoundException;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@FeignClient(name = "Vehicle--Service")
//
//public interface VehicleClient {
//
//    @GetMapping("/{vehicleId}")
//    VehicleDTO getVehicleById(@PathVariable("vehicleId") int vehicleId);
//}

package com.capgemini.client;

import com.capgemini.dto.VehicleDTO;
import com.capgemini.exception.VehicleNotFoundException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "Vehicle-Service")
public interface VehicleClient {

    @GetMapping("/vehicles/{vehicleId}")
    VehicleDTO getVehicleById(@PathVariable("vehicleId") int vehicleId) throws VehicleNotFoundException;
}