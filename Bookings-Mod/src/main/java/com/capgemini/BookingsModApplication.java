package com.capgemini;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
//@EnableAutoConfiguration
@EnableFeignClients
@EnableDiscoveryClient
public class BookingsModApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookingsModApplication.class, args);
	}

}