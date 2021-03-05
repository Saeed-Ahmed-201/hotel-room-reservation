package com.hotel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.hotel"})
public class   HotelApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(HotelApplication.class, args);
	}

}
