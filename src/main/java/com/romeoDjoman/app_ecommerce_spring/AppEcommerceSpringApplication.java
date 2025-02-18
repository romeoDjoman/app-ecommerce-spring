package com.romeoDjoman.app_ecommerce_spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class AppEcommerceSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppEcommerceSpringApplication.class, args);
	}

}