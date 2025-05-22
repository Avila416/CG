package com.capgemini.service;

import java.util.List;
import java.util.Optional;

import com.capgemini.dto.VehicleDTO;
import com.capgemini.entity.Vehicle;
import com.capgemini.entity.VehicleStatus;

public interface Ivehicle {
	public VehicleDTO registerVehicle(VehicleDTO vehicleDTO);
    public List<VehicleDTO> getAvailableVehicles();
    public List<VehicleDTO> getAll();
    public VehicleDTO getVehicleById(int id);
    
    public VehicleDTO updateVehicleStatus(int id, VehicleStatus status);
	void deleteVehicle(int id);
	VehicleDTO updateVehicle(int id, VehicleDTO vehicleDTO);
	
	
// public VehicleDTO updateVehicleStatus(int id, String status);
	
}