package com.grupo7.application.controller;

import org.springframework.web.bind.annotation.*;
import com.grupo7.application.service.GestorRevisionManual;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api")
public class GestorRevisionManualController {

    private final GestorRevisionManual gestorRevisionManual;

    @Autowired
    public GestorRevisionManualController(GestorRevisionManual gestorRevisionManual) {
        this.gestorRevisionManual = gestorRevisionManual;
    }

    @GetMapping("/EventosSismicosAutoDetectados")
    public String ping() {
        return "✅ Server is running!";
    }

    @GetMapping("/hello")
    public String sayHello() {
        // Simple DB access: count rows in ESTADO table
        try (java.sql.Connection conn = java.sql.DriverManager.getConnection(
                "jdbc:h2:file:./backend/data/redSismica;DB_CLOSE_DELAY=-1", "sa", "");
             java.sql.Statement stmt = conn.createStatement();
             java.sql.ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM ESTADO")) {
            rs.next();
            int count = rs.getInt(1);
            return "👋 Hello from Spring Boot! Hay " + count + " estados en la base de datos.";
        } catch (Exception e) {
            return "Error accediendo a la base de datos: " + e.getMessage();
        }
    }

    @PostMapping("/iniciar-programa")
    public String iniciarPrograma() {
        gestorRevisionManual.buscarEventosSismicosNoRevisados();
        return "Programa iniciado: búsqueda de eventos sísmicos no revisados ejecutada.";
    }

    @GetMapping("/registrarRevisionManual")
    public String registrarRevisionManual() {
        gestorRevisionManual.registrarRevisionManual();
        return "Revisión manual registrada";
    }

    @PostMapping("/tomar-no-visualizacion")
    public ResponseEntity<String> tomarNoVisualizacion() {
        gestorRevisionManual.tomarNoVisualizacion();
        return ResponseEntity.ok("No visualización registrada");
    }
}