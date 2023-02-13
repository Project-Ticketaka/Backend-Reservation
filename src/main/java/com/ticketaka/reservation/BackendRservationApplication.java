package com.ticketaka.reservation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class BackendRservationApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendRservationApplication.class, args);
	}

}
