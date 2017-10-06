package com.symphony.hackathon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class SymphonyHackathonApplication {

	public static void main(String[] args) {
		SpringApplication.run(SymphonyHackathonApplication.class, args);
	}
}
