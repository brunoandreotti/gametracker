package com.brunoandreotti.gametracker;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "GameTracker-api", version = "1.0", description = "API GameTracker"))
public class GametrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(GametrackerApplication.class, args);
	}

}
