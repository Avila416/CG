package com.capgemini.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.dto.VehicleDTO;
import com.capgemini.entity.Vehicle;
import com.capgemini.entity.VehicleStatus;
import com.capgemini.exception.VehicleNotFoundException;
import com.capgemini.repository.VehicleRepository;

@Service
public class VehicleServiceImpul implements Ivehicle {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public VehicleDTO getVehicleById(int id) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new VehicleNotFoundException("Vehicle not found with ID: " + id));
        return convertToDTO(vehicle);
    }

    @Override
    public VehicleDTO registerVehicle(VehicleDTO vehicleDTO) {
        Vehicle vehicle = convertToEntity(vehicleDTO);
        vehicle = vehicleRepository.save(vehicle);
        return convertToDTO(vehicle);
    }

    @Override
    public VehicleDTO updateVehicle(int id, VehicleDTO vehicleDTO) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new VehicleNotFoundException("Vehicle not found with ID: " + id));

        vehicle.setBrand(vehicleDTO.getBrand());
        vehicle.setModel(vehicleDTO.getModel());
        vehicle.setType(vehicleDTO.getType());
        vehicle.setRegistrationNumber(vehicleDTO.getRegistrationNumber());
        vehicle.setDriverId(vehicleDTO.getDriverId());
        vehicle.setStatus(vehicleDTO.getStatus());  // status updated with the provided value

        vehicle = vehicleRepository.save(vehicle);
        return convertToDTO(vehicle);
    }

    @Override
    public void deleteVehicle(int id) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new VehicleNotFoundException("Vehicle not found with ID: " + id));
        vehicleRepository.delete(vehicle);
    }

    @Override
    public List<VehicleDTO> getAvailableVehicles() {
        List<Vehicle> availableVehicles = vehicleRepository.findByStatus(VehicleStatus.AVAILABLE);
        return availableVehicles.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public VehicleDTO updateVehicleStatus(int id, VehicleStatus status) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new VehicleNotFoundException("Vehicle not found with ID: " + id));

        vehicle.setStatus(status);
        vehicle = vehicleRepository.save(vehicle);
        return convertToDTO(vehicle);
    }

    private VehicleDTO convertToDTO(Vehicle vehicle) {
        return new VehicleDTO(vehicle.getVehicleId(), vehicle.getBrand(), vehicle.getModel(), vehicle.getType(),
                vehicle.getRegistrationNumber(), vehicle.getDriverId(), vehicle.getStatus());
    }

    private Vehicle convertToEntity(VehicleDTO vehicleDTO) {
        return new Vehicle(vehicleDTO.getVehicleId(), vehicleDTO.getBrand(), vehicleDTO.getModel(),
                vehicleDTO.getType(), vehicleDTO.getRegistrationNumber(), vehicleDTO.getDriverId(),
                vehicleDTO.getStatus());
    }

    @Override
    public List<VehicleDTO> getAll() {
        List<Vehicle> vehicles = vehicleRepository.findAll();
        return vehicles.stream().map(this::convertToDTO).collect(Collectors.toList());
    }
}