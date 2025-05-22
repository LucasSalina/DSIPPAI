package com.grupo7.application.controller;

import com.grupo7.application.entity.CambioEstado;
import com.grupo7.application.service.CambioEstadoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cambioestado")
public class CambioEstadoController {

    private final CambioEstadoService service;

    public CambioEstadoController(CambioEstadoService service) {
        this.service = service;
    }

    @GetMapping
    public List<CambioEstado> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public CambioEstado getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public CambioEstado create(@RequestBody CambioEstado cambioEstado) {
        return service.save(cambioEstado);
    }

    @PutMapping("/{id}")
    public CambioEstado update(@PathVariable Long id, @RequestBody CambioEstado cambioEstado) {
        CambioEstado existing = service.findById(id);
        if (existing == null) {
            return null;
        }
        existing.setFechaHoraInicio(cambioEstado.getFechaHoraInicio());
        existing.setFechaHoraFin(cambioEstado.getFechaHoraFin());
        existing.setEstado(cambioEstado.getEstado());
        existing.setResponsable(cambioEstado.getResponsable());
        return service.save(existing);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteById(id);
    }
}
