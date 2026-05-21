package com.aishop.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AiShoppingBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(AiShoppingBackendApplication.class, args);

		// Swagger UI 접속 경로 출력 로직
		System.out.println();
		System.out.println("==============================================");
		System.out.println("AI Shopping Backend started successfully.");
		System.out.println("Swagger UI: http://localhost:8080/swagger-ui.html");
		System.out.println("OpenAPI Docs: http://localhost:8080/v3/api-docs");
		System.out.println("==============================================");
		System.out.println();
	}

}
