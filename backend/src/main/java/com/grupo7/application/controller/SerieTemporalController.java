package com.grupo7.application.controller;

import com.grupo7.application.entity.SerieTemporal;
import com.grupo7.application.service.SerieTemporalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/series-temporales")
public class SerieTemporalController {

    private final SerieTemporalService serieTemporalService;

    @Autowired
    public SerieTemporalController(SerieTemporalService serieTemporalService) {
        this.serieTemporalService = serieTemporalService;
    }

    @GetMapping
    public ResponseEntity<List<SerieTemporal>> obtenerTodasSeriesTemporales() {
        List<SerieTemporal> series = serieTemporalService.obtenerTodas();
        return new ResponseEntity<>(series, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SerieTemporal> obtenerSerieTemporalPorId(@PathVariable("id") Integer id) { // FIX: Changed Long to Integer
        Optional<SerieTemporal> serieTemporal = serieTemporalService.obtenerPorId(id);
        return serieTemporal.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<SerieTemporal> crearSerieTemporal(@RequestBody SerieTemporal serieTemporal) {
        SerieTemporal nuevaSerieTemporal = serieTemporalService.crear(serieTemporal);
        return new ResponseEntity<>(nuevaSerieTemporal, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SerieTemporal> actualizarSerieTemporal(@PathVariable("id") Integer id, // FIX: Changed Long to Integer
                                                                 @RequestBody SerieTemporal serieTemporal) {
        try {
            SerieTemporal actualizada = serieTemporalService.actualizar(id, serieTemporal);
            return new ResponseEntity<>(actualizada, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Or an appropriate error status
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarSerieTemporal(@PathVariable("id") Integer id) { // FIX: Changed Long to Integer
        try {
            serieTemporalService.eliminar(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Or internal server error
        }
    }

    // This endpoint is for getting data related to a specific SerieTemporal by its ID
    @GetMapping("/{id}/datos")
    public ResponseEntity<Object> obtenerDatosDeSerie(@PathVariable("id") Integer id) { // FIX: Changed Long to Integer, and expects ID
        // The service method expects an Integer ID
        Object datos = serieTemporalService.obtenerDatosDeSerie(id); // FIX: Passing the ID, not the object
        if (datos != null) {
            return new ResponseEntity<>(datos, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // No data found for this ID
        }
    }
}