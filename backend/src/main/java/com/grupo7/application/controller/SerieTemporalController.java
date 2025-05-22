package com.grupo7.application.controller;

import com.grupo7.application.entity.SerieTemporal;
import com.grupo7.application.service.SerieTemporalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/serie-temporal")
public class SerieTemporalController {

    private final SerieTemporalService serieTemporalService;

    @Autowired
    public SerieTemporalController(SerieTemporalService serieTemporalService) {
        this.serieTemporalService = serieTemporalService;
    }

    @GetMapping
    public List<SerieTemporal> obtenerTodas() {
        return serieTemporalService.obtenerTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SerieTemporal> obtenerPorId(@PathVariable Long id) {
        return serieTemporalService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<SerieTemporal> crear(@RequestBody SerieTemporal nuevaSerie) {
        return ResponseEntity.ok(serieTemporalService.crear(nuevaSerie));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SerieTemporal> actualizar(@PathVariable Long id, @RequestBody SerieTemporal actualizada) {
        try {
            return ResponseEntity.ok(serieTemporalService.actualizar(id, actualizada));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        serieTemporalService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/datos")
    public ResponseEntity<List<Object>> obtenerDatos(@RequestBody SerieTemporal serieTemporal) {
        List<Object> datos = serieTemporalService.obtenerDatosDeSerie(serieTemporal);
        return ResponseEntity.ok(datos);
    }
}
