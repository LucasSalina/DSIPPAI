package com.grupo7.application.controller;

import com.grupo7.application.entity.EventoSismico;
import com.grupo7.application.service.EventoSismicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/eventos")
public class EventoSismicoController {

    @Autowired
    private EventoSismicoService service;

    @GetMapping
    public List<EventoSismico> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Optional<EventoSismico> getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public EventoSismico create(@RequestBody EventoSismico evento) {
        return service.save(evento);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
