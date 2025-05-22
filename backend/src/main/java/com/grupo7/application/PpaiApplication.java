package com.grupo7.application;

import com.grupo7.application.service.GestorRevisionManual;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Bean;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
@ComponentScan(basePackages = "com.grupo7.application")
public class PpaiApplication {
    public static void main(String[] args) {
        SpringApplication.run(PpaiApplication.class, args);
        GestorRevisionManual gestorRevisionManual = GestorRevisionManual.crearGestorConEventosAleatorios();
        gestorRevisionManual.registrarRevisionManual();
    }
    @Bean
    public GestorRevisionManual gestorRevisionManual() {
        return new GestorRevisionManual();
    }
}