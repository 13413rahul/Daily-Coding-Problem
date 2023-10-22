package com.rahul.daily_coding_problem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DailyCodingProblemApplication {

	public static void main(String[] args) {
		SpringApplication.run(DailyCodingProblemApplication.class, args);
	}

}
