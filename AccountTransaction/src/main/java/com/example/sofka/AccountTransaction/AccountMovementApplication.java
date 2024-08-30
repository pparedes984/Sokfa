package com.example.sofka.AccountTransaction;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@SpringBootApplication(scanBasePackages = "com.example.sofka.AccountTransaction")
@EnableR2dbcRepositories(basePackages = "com.example.sofka.AccountTransaction.repository")
public class AccountMovementApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountMovementApplication.class, args);
	}

	@PostConstruct
	public void init() {
		System.out.println("Application started");
	}

}
