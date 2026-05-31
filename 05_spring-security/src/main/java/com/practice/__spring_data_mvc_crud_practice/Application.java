package com.practice.__spring_data_mvc_crud_practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication(exclude = {
//		// disable authentication default of Spring Security
//		org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
//})

@SpringBootApplication()
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
