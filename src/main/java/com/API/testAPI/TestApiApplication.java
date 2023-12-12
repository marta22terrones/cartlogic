package com.API.testAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TestApiApplication {

	public static void main(String[] args) {

		SpringApplication.run(TestApiApplication.class, args);
		System.out.println("Scheduled task for cart cleanup is enabled.");
	}

}
