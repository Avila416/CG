package com.vms.client;



import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.vms.DTO.BookingDTO;

@FeignClient(name = "booking-services")
public interface BookingFeignClient {
   

    @GetMapping("/bookings/{id}")
     BookingDTO getBookingById(@PathVariable("id") int bookingId);//method to get the boooking details
}
