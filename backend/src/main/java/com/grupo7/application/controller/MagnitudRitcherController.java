package com.grupo7.application.controller;

import com.grupo7.application.entity.MagnitudRitcher;
import com.grupo7.application.service.MagnitudRitcherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/magnitudes")
public class MagnitudRitcherController {

    private final MagnitudRitcherService magnitudRitcherService;

    @Autowired
    public MagnitudRitcherController(MagnitudRitcherService magnitudRitcherService) {
        this.magnitudRitcherService = magnitudRitcherService;
    }

    @GetMapping
    public List<MagnitudRitcher> obtenerTodos() {
        return magnitudRitcherService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Optional<MagnitudRitcher> obtenerPorId(@PathVariable Long id) {
        return magnitudRitcherService.obtenerPorId(id);
    }

    @PostMapping
    public MagnitudRitcher crear(@RequestBody MagnitudRitcher magnitud) {
        return magnitudRitcherService.guardar(magnitud);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        magnitudRitcherService.eliminar(id);
    }
}
