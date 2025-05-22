package com.grupo7.application.controller;

import com.grupo7.application.entity.EstacionSismologica;
import com.grupo7.application.service.EstacionSismologicaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estacion")
public class EstacionSismologicaController {

    private final EstacionSismologicaService service;

    public EstacionSismologicaController(EstacionSismologicaService service) {
        this.service = service;
    }

    @GetMapping
    public List<EstacionSismologica> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public EstacionSismologica getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public EstacionSismologica create(@RequestBody EstacionSismologica estacion) {
        return service.save(estacion);
    }

    @PutMapping("/{id}")
    public EstacionSismologica update(@PathVariable Long id, @RequestBody EstacionSismologica estacion) {
        EstacionSismologica existing = service.findById(id);
        if (existing == null) return null;
        existing.setCodigoEstacion(estacion.getCodigoEstacion());
        existing.setNombre(estacion.getNombre());
        existing.setLatitud(estacion.getLatitud());
        existing.setLongitud(estacion.getLongitud());
        existing.setDocumentoCertificaionAdq(estacion.getDocumentoCertificaionAdq());
        existing.setFechaSolicitudCertificacion(estacion.getFechaSolicitudCertificacion());
        existing.setNroCertificacionAdquisicion(estacion.getNroCertificacionAdquisicion());
        return service.save(existing);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteById(id);
    }
}
