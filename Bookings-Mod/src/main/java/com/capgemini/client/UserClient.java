package com.capgemini.client;

import com.capgemini.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "USER-SERVICE")  // Add the configuration here
public interface UserClient {

    // Get a user by their ID
    @GetMapping("/api/users/view/{id}")
    UserDTO getUserById(@PathVariable("id") Long id);
}
