package com.project.SoutienScolaire;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;

import jakarta.servlet.MultipartConfigElement;

@SpringBootApplication
public class SoutienScolaireApplication {

	public static void main(String[] args) {
		SpringApplication.run(SoutienScolaireApplication.class, args);
	}

	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		// Set maximum file size here if needed
		return factory.createMultipartConfig();
	}

}
