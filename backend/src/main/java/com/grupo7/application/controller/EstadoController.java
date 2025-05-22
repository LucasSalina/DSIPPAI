package com.grupo7.application.controller;

import com.grupo7.application.entity.Estado;
import com.grupo7.application.service.EstadoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estado")
public class EstadoController {

    private final EstadoService service;

    public EstadoController(EstadoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Estado> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Estado getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public Estado create(@RequestBody Estado estado) {
        return service.save(estado);
    }

    @PutMapping("/{id}")
    public Estado update(@PathVariable Long id, @RequestBody Estado estado) {
        Estado existing = service.findById(id);
        if (existing == null) return null;
        existing.setAmbito(estado.getAmbito());
        existing.setNombreEstado(estado.getNombreEstado());
        return service.save(existing);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteById(id);
    }
}
