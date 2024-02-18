package com.project.SoutienScolaire;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import jakarta.servlet.MultipartConfigElement;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
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
