package com.slokam.da.hc;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
@OpenAPIDefinition(info = @Info(title = "HealthCare API",
version = "1.0", 
description = "Provides All Scirpt related API"))
@SpringBootApplication
@EnableScheduling
@EnableTransactionManagement
public class HcApplication {

	public static void main(String[] args) {
		SpringApplication.run(HcApplication.class, args);
	}

}
