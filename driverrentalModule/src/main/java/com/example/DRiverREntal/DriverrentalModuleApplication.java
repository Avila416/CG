package com.example.DRiverREntal;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


//Main entry point for the Driver Rental Module application

@SpringBootApplication
@EnableAutoConfiguration
@EnableDiscoveryClient
public class DriverrentalModuleApplication {

	public static void main(String[] args) {
		 // Start the Spring Boot application
		SpringApplication.run(DriverrentalModuleApplication.class, args);
	}

}


 
