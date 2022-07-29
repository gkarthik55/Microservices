package com.microservices.CitizenService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CitizenServiceApplication 
{
	public static void main(String[] args) 
	{
		System.out.println("Welcome to Spring Boot");
		
		SpringApplication.run(CitizenServiceApplication.class, args);
	}
}
