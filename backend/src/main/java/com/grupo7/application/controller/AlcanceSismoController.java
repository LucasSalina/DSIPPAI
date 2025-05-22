package com.grupo7.application.controller;

import com.grupo7.application.entity.AlcanceSismo;
import com.grupo7.application.service.AlcanceSismoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alcancesismo")
public class AlcanceSismoController {

    private final AlcanceSismoService service;

    public AlcanceSismoController(AlcanceSismoService service) {
        this.service = service;
    }

    @GetMapping
    public List<AlcanceSismo> getAll() {
        return service.findAll();
    }

    @GetMapping("/{nombre}")
    public AlcanceSismo getById(@PathVariable String nombre) {
        return service.findById(nombre);
    }

    @PostMapping
    public AlcanceSismo create(@RequestBody AlcanceSismo alcanceSismo) {
        return service.save(alcanceSismo);
    }

    @PutMapping("/{nombre}")
    public AlcanceSismo update(@PathVariable String nombre, @RequestBody AlcanceSismo alcanceSismo) {
        AlcanceSismo existing = service.findById(nombre);
        if (existing == null) {
            return null; // o lanzar excepción 404
        }
        existing.setDescripcion(alcanceSismo.getDescripcion());
        return service.save(existing);
    }

    @DeleteMapping("/{nombre}")
    public void delete(@PathVariable String nombre) {
        service.deleteById(nombre);
    }
}
