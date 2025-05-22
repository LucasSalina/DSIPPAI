package com.grupo7.application.controller;

import com.grupo7.application.service.GestorRevisionManual;
import com.grupo7.application.entity.EventoSismico;
import com.grupo7.application.dto.DatosPrincipalesDTO;
import com.grupo7.application.dto.DatosRegistradosDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/revision-manual")
public class GestorRevisionManualController {

    private final GestorRevisionManual gestorRevisionManual;

    @Autowired
    public GestorRevisionManualController(GestorRevisionManual gestorRevisionManual) {
        this.gestorRevisionManual = gestorRevisionManual;
    }

    @GetMapping("/eventos-no-revisados")
    public ResponseEntity<String> getEventosSismicosNoRevisados() {
        try {
            String jsonResponse = gestorRevisionManual.buscarEventosSismicosNoRevisados();
            return ResponseEntity.ok()
                                 .header("Content-Type", "application/json")
                                 .body(jsonResponse);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("{\"error\": \"Error al buscar eventos sísmicos no revisados: " + e.getMessage() + "\"}");
        }
    }

    @PostMapping("/seleccionar-evento/{eventId}")
    public ResponseEntity<?> seleccionarEventoSismico(@PathVariable Long eventId) { // Changed return type to ResponseEntity<?> for flexibility
        try {
            // 1. Select the event (this will set the internal selected event)
            gestorRevisionManual.tomarSeleccionEventoSismico(eventId);

            // 2. Block the selected event for revision (this saves the state change)
            gestorRevisionManual.bloquearEventoSismicoSeleccionado();

            // 3. Get the data you want to send back
            // Assuming buscarDatosRegistrados() retrieves data for the *currently selected* event
            DatosRegistradosDTO datosRegistrados = gestorRevisionManual.buscarDatosRegistrados();

            if (datosRegistrados == null) {
                // If no registered data is found for the selected event, you might want to return 404 or a specific message
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                     .body("{\"error\": \"Evento sísmico seleccionado, pero no se encontraron datos registrados asociados.\"}");
            }

            // 4. Return a 200 OK status with the retrieved DTO in the response body
            // Spring will automatically convert the DTO to JSON
            return ResponseEntity.ok(datosRegistrados); // Now returning the DTO directly

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("{\"error\": \"" + e.getMessage() + "\"}");
        } catch (IllegalStateException e) {
            // Catches issues like states not found, or employee not initialized, etc.
            return ResponseEntity.status(HttpStatus.CONFLICT) // 409 Conflict is often appropriate for state issues
                                 .body("{\"error\": \"" + e.getMessage() + "\"}");
        } catch (org.springframework.dao.DataIntegrityViolationException e) {
            // This specifically catches database constraint violations, like primary key conflicts.
            // This is the error we were tackling related to CambioEstado.
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                 .body("{\"error\": \"Error de datos: Ya existe un registro similar o el ID está duplicado. Detalles: " + e.getMostSpecificCause().getMessage() + "\"}");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("{\"error\": \"Error inesperado al seleccionar y bloquear el evento sísmico: " + e.getMessage() + "\"}");
        }
    }

    @GetMapping("/datos-registrados")
    public ResponseEntity<DatosRegistradosDTO> getDatosRegistrados() {
        DatosRegistradosDTO datos = gestorRevisionManual.buscarDatosRegistrados();
        if (datos == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(datos);
    }

    @PostMapping("/rechazar-evento")
    public ResponseEntity<String> rechazarEventoSismico() {
        if (gestorRevisionManual.getEventoSismicoSeleccionado() == null) {
            return ResponseEntity.badRequest().body("{\"message\": \"No hay evento sísmico seleccionado para rechazar.\"}");
        }

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

    @GetMapping("/eventos-en-revision")
    public ResponseEntity<List<EventoSismico>> getEventosEnRevision() {
        List<EventoSismico> eventos = gestorRevisionManual.obtenerEventosEnRevision();
        return ResponseEntity.ok(eventos);
    }

    @GetMapping("/eventos-rechazados")
    public ResponseEntity<List<EventoSismico>> getEventosRechazados() {
        List<EventoSismico> eventos = gestorRevisionManual.obtenerEventosRechazados();
        return ResponseEntity.ok(eventos);
    }
}