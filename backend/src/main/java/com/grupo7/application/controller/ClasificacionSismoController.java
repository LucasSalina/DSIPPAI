package com.grupo7.application.controller;

import com.grupo7.application.entity.ClasificacionSismo;
import com.grupo7.application.service.ClasificacionSismoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clasificacionsismo")
public class ClasificacionSismoController {

    private final ClasificacionSismoService service;

    public ClasificacionSismoController(ClasificacionSismoService service) {
        this.service = service;
    }

    @GetMapping
    public List<ClasificacionSismo> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ClasificacionSismo getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public ClasificacionSismo create(@RequestBody ClasificacionSismo entity) {
        return service.save(entity);
    }

    @PutMapping("/{id}")
    public ClasificacionSismo update(@PathVariable Long id, @RequestBody ClasificacionSismo entity) {
        ClasificacionSismo existing = service.findById(id);
        if (existing == null) {
            return null;
        }
        existing.setKmProfundidadDesde(entity.getKmProfundidadDesde());
        existing.setKmProfundidadHasta(entity.getKmProfundidadHasta());
        existing.setNombre(entity.getNombre());
        return service.save(existing);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteById(id);
    }
}
