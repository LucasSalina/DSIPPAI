
package com.grupo7.application.controller;

import com.grupo7.application.entity.MuestraSismica;
import com.grupo7.application.service.MuestraSismicaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/muestras")
public class MuestraSismicaController {

    private final MuestraSismicaService service;

    public MuestraSismicaController(MuestraSismicaService service) {
        this.service = service;
    }

    @GetMapping
    public List<MuestraSismica> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Optional<MuestraSismica> getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public MuestraSismica create(@RequestBody MuestraSismica muestra) {
        return service.save(muestra);
    }

    @PutMapping("/{id}")
    public MuestraSismica update(@PathVariable Long id, @RequestBody MuestraSismica muestra) {
        Optional<MuestraSismica> existing = service.findById(id);
        if (existing.isEmpty()) {
            return null;
        }
        MuestraSismica m = existing.get();
        m.setFechaHoraMuestra(muestra.getFechaHoraMuestra());
        m.setDetalleMuestra(muestra.getDetalleMuestra());
        return service.save(m);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteById(id);
    }
}
