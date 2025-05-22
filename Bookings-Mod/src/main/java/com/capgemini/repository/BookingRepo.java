package com.capgemini.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.entity.Booking;

public interface BookingRepo extends JpaRepository<Booking,Integer> {
	
    List<Booking> findByVehicleId(int vehicleId);
	
	List<Booking> findByBookingDate(LocalDate bookingDate);

    List<Booking> findByUserId(Long userId);

}
