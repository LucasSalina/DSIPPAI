package com.grupo7.application.controller;

import com.grupo7.application.entity.DetalleMuestraSismica;
import com.grupo7.application.service.DetalleMuestraSismicaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detallemuestrasismica")
public class DetalleMuestraSismicaController {

    private final DetalleMuestraSismicaService service;

    public DetalleMuestraSismicaController(DetalleMuestraSismicaService service) {
        this.service = service;
    }

    @GetMapping
    public List<DetalleMuestraSismica> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public DetalleMuestraSismica getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public DetalleMuestraSismica create(@RequestBody DetalleMuestraSismica entity) {
        return service.save(entity);
    }

    @PutMapping("/{id}")
    public DetalleMuestraSismica update(@PathVariable Long id, @RequestBody DetalleMuestraSismica entity) {
        DetalleMuestraSismica existing = service.findById(id);
        if (existing == null) {
            return null;
        }
        existing.setValor(entity.getValor());
        existing.setTipoDeDato(entity.getTipoDeDato());
        return service.save(existing);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteById(id);
    }
}
