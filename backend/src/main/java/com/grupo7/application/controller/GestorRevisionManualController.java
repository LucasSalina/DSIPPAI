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
    public ResponseEntity<String> seleccionarEventoSismico(@PathVariable Long eventId) {
        try {
            // First, select the event in the service
            gestorRevisionManual.tomarSeleccionEventoSismico(eventId);

            // Then, block the selected event for revision
            gestorRevisionManual.bloquearEventoSismicoSeleccionado();
            
            return ResponseEntity.ok("{\"message\": \"Evento Sismico con ID " + eventId + " seleccionado y bloqueado para revisión.\"}");

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("{\"error\": \"" + e.getMessage() + "\"}");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("{\"error\": \"" + e.getMessage() + "\"}");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("{\"error\": \"Error al seleccionar y bloquear el evento sísmico: " + e.getMessage() + "\"}");
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