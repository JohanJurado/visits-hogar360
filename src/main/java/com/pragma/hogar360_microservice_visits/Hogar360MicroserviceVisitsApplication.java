package com.pragma.hogar360_microservice_visits;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class Hogar360MicroserviceVisitsApplication {

	public static void main(String[] args) {
		SpringApplication.run(Hogar360MicroserviceVisitsApplication.class, args);
	}

}
