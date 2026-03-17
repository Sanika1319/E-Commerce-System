package com.ECommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ECommerceSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ECommerceSystemApplication.class, args);
	}

}
