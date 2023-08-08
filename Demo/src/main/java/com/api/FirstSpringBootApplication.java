package com.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.GetMapping;

@ComponentScan(basePackages = "com.api")
@EnableJpaRepositories(basePackages = "com.api.repository")
@EntityScan(basePackages = "com.api.entity")
@SpringBootApplication
public class FirstSpringBootApplication {
	@GetMapping("/home1")
	public static void main(String[] args) {
		SpringApplication.run(FirstSpringBootApplication.class, args);
		System.out.println("Hello World!");

	}

}
