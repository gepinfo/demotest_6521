package com.default_service.gcam;

import com.default_service.gcam.service.GcamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Slf4j
public class GcamApplication {

	public static void main(String[] args) {
		SpringApplication.run(GcamApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(GcamService gcamService) {
		return args -> {
			String filesPath="/app/src/main/resources/";
			gcamService.saveResourceAndScreensFromJson(filesPath);
		};
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedMethods("*")
				.allowedOrigins("*")
				.allowedHeaders("*");
			}
		};
	}
}
