package com.grupo7.application.controller;

import com.grupo7.application.service.GestorRevisionManual;      // REQUIRED IMPORT
import com.grupo7.application.entity.EventoSismico;            // REQUIRED IMPORT
import com.grupo7.application.dto.DatosPrincipalesDTO;         // Potentially needed if you expose DTOs
import com.grupo7.application.dto.DatosRegistradosDTO;         // Potentially needed if you expose DTOs

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional; // REQUIRED IMPORT


@RestController
@RequestMapping("/api/revision-manual") // This is a common practice to prefix API endpoints
public class GestorRevisionManualController {

    private final GestorRevisionManual gestorRevisionManual;

    // Spring recommends constructor injection for dependencies
    @Autowired
    public GestorRevisionManualController(GestorRevisionManual gestorRevisionManual) {
        this.gestorRevisionManual = gestorRevisionManual;
    }

    // Example endpoint to trigger the search for non-reviewed seismic events
    // This method now returns the JSON string from the service
    @GetMapping("/eventos-no-revisados")
    public ResponseEntity<String> getEventosSismicosNoRevisados() {
        try {
            String jsonResponse = gestorRevisionManual.buscarEventosSismicosNoRevisados();
            return ResponseEntity.ok()
                                 .header("Content-Type", "application/json") // Ensure content type is JSON
                                 .body(jsonResponse);
        } catch (Exception e) {
            // Log the error for debugging
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("{\"error\": \"Error al buscar eventos sísmicos no revisados: " + e.getMessage() + "\"}");
        }
    }

    // Example endpoint to select an event
    @PostMapping("/seleccionar-evento/{eventId}")
    public ResponseEntity<String> seleccionarEventoSismico(@PathVariable Long eventId) {
        gestorRevisionManual.tomarSeleccionEventoSismico(eventId);
        // Using the public getter now: getEventoSismicoSeleccionado()
        if (gestorRevisionManual.getEventoSismicoSeleccionado() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"Evento Sismico con ID " + eventId + " no encontrado.\"}");
        }
        return ResponseEntity.ok("{\"message\": \"Evento Sismico con ID " + eventId + " seleccionado.\"}");
    }

    // Example endpoint to get registered data for a selected event
    @GetMapping("/datos-registrados")
    public ResponseEntity<DatosRegistradosDTO> getDatosRegistrados() {
        DatosRegistradosDTO datos = gestorRevisionManual.buscarDatosRegistrados();
        if (datos == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(datos);
    }

    // Example endpoint to reject an event
    @PostMapping("/rechazar-evento")
    public ResponseEntity<String> rechazarEventoSismico() {
        // For simplicity, this assumes an event has already been selected by a previous call.
        // Using the public getter now: getEventoSismicoSeleccionado()
        if (gestorRevisionManual.getEventoSismicoSeleccionado() == null) {
            return ResponseEntity.badRequest().body("{\"message\": \"No hay evento sísmico seleccionado para rechazar.\"}");
        }

        // Validate if the action is valid, assuming "rechazar evento" is an action string
        boolean isValid = gestorRevisionManual.validarDatosSismicos("rechazar evento");
        if (!isValid) {
            return ResponseEntity.badRequest().body("{\"message\": \"Validación de datos sísmicos fallida para rechazo.\"}");
        }

        try {
            gestorRevisionManual.actualizarEventoSismicoARechazado();
            return ResponseEntity.ok("{\"message\": \"Evento sísmico rechazado exitosamente.\"}");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("{\"error\": \"Error al rechazar el evento sísmico: " + e.getMessage() + "\"}");
        }
    }

    // Example endpoint to get events in revision
    @GetMapping("/eventos-en-revision")
    public ResponseEntity<List<EventoSismico>> getEventosEnRevision() {
        List<EventoSismico> eventos = gestorRevisionManual.obtenerEventosEnRevision();
        return ResponseEntity.ok(eventos);
    }

    // Example endpoint to get rejected events
    @GetMapping("/eventos-rechazados")
    public ResponseEntity<List<EventoSismico>> getEventosRechazados() {
        List<EventoSismico> eventos = gestorRevisionManual.obtenerEventosRechazados();
        return ResponseEntity.ok(eventos);
    }

    // You would add more endpoints as needed for your application's flow
}