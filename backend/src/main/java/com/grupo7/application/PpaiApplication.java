package com.grupo7.application;

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
    }

    // Consulta simple para forzar la creación de la base de datos y verificar acceso
    @PostConstruct
    public void testConexionBaseDatos() {
        try {
            java.sql.Connection conn = java.sql.DriverManager.getConnection(
                "jdbc:h2:file:./backend/data/redSismica;DB_CLOSE_DELAY=-1", "sa", "");
            java.sql.Statement stmt = conn.createStatement();
            stmt.executeQuery("SELECT 1");
            System.out.println("Conexión a la base de datos H2 (sin modo SQLite) exitosa.");
            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.err.println("Error al conectar a la base de datos (sin modo SQLite): " + e.getMessage());
        }
    }

    @PostConstruct
    public void printH2Version() {
        try {
            Package h2Package = Class.forName("org.h2.Driver").getPackage();
            String version = h2Package.getImplementationVersion();
            System.out.println("[DEBUG] H2 version en runtime: " + version);
        } catch (Exception e) {
            System.out.println("[DEBUG] No se pudo obtener la versión de H2: " + e.getMessage());
        }
    }

    @Bean
    public GestorRevisionManual gestorRevisionManual() {
        return new GestorRevisionManual();
    }
}