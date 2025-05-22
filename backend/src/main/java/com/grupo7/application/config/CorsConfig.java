package com.grupo7.application.config; // Ensure this package is scanned

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // This applies to ALL paths under your application context
                        .allowedOrigins("http://localhost:3000") // EXACTLY this origin
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Include all methods your frontend might use
                        .allowedHeaders("*") // Allow all headers
                        .allowCredentials(true); // Crucial if you're sending cookies or auth headers
            }
        };
    }
}