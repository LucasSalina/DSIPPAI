package com.grupo7.application;

import com.grupo7.application.entity.Estado; // <-- ADD THIS IMPORT for Estado entity
import com.grupo7.application.repository.EstadoRepository; // <-- ADD THIS IMPORT for EstadoRepository
import org.springframework.boot.CommandLineRunner; // <-- ADD THIS IMPORT for CommandLineRunner
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean; // <-- ADD THIS IMPORT for @Bean
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.grupo7.application")
public class PpaiApplication {
    public static void main(String[] args) {
        SpringApplication.run(PpaiApplication.class, args);
    }
    @Bean
    CommandLineRunner initStates(EstadoRepository estadoRepository) {
        return args -> {
            // Use findByAmbitoAndNombreEstado to check existence
            // If they don't exist, save them.
            if (estadoRepository.findByAmbitoAndNombreEstado("EventoSismico", "AutoDetectado").isEmpty()) {
                estadoRepository.save(new Estado("EventoSismico", "AutoDetectado"));
            }
            if (estadoRepository.findByAmbitoAndNombreEstado("EventoSismico", "PendienteRevision").isEmpty()) {
                estadoRepository.save(new Estado("EventoSismico", "PendienteRevision"));
            }
            if (estadoRepository.findByAmbitoAndNombreEstado("EventoSismico", "BloqueadoEnRevision").isEmpty()) {
                estadoRepository.save(new Estado("EventoSismico", "BloqueadoEnRevision"));
            }
            if (estadoRepository.findByAmbitoAndNombreEstado("EventoSismico", "Rechazado").isEmpty()) {
                estadoRepository.save(new Estado("EventoSismico", "Rechazado"));
            }
            // Add any other Estado objects you need in your database
            System.out.println("Initialized/verified Estado entities in DB.");
        };
    }
}