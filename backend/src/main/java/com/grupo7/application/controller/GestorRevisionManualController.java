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
        return "Hello World!";
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