package com.grupo7.application.controller;

import com.grupo7.application.entity.OrigenDeGeneracion;
import com.grupo7.application.service.OrigenDeGeneracionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/origenes")
public class OrigenDeGeneracionController {

    @Autowired
    private OrigenDeGeneracionService service;

    @GetMapping
    public List<OrigenDeGeneracion> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrigenDeGeneracion> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public OrigenDeGeneracion create(@RequestBody OrigenDeGeneracion origen) {
        return service.save(origen);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrigenDeGeneracion> update(@PathVariable Long id, @RequestBody OrigenDeGeneracion origen) {
        return service.findById(id).map(o -> {
            o.setDescripcion(origen.getDescripcion());
            o.setNombre(origen.getNombre());
            return ResponseEntity.ok(service.save(o));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (service.findById(id).isPresent()) {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
