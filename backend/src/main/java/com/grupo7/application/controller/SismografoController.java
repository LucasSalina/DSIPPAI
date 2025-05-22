package com.grupo7.application.controller;

import com.grupo7.application.entity.Sismografo;
import com.grupo7.application.service.SismografoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sismografos")
public class SismografoController {

    private final SismografoService servicio;

    public SismografoController(SismografoService servicio) {
        this.servicio = servicio;
    }

    @GetMapping("/mostrar")
    public List<Sismografo> listarSismografos() {
        return servicio.obtenerTodos();
    }

    @PostMapping
    public Sismografo crear(@RequestBody Sismografo sismografo) {
        return servicio.guardar(sismografo);
    }
}
