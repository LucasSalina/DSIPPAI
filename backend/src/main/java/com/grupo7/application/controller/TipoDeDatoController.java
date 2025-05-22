package com.grupo7.application.controller;

import com.grupo7.application.entity.TipoDeDato;
import com.grupo7.application.service.TipoDeDatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipos-de-dato")
public class TipoDeDatoController {

    private final TipoDeDatoService tipoDeDatoService;

    @Autowired
    public TipoDeDatoController(TipoDeDatoService tipoDeDatoService) {
        this.tipoDeDatoService = tipoDeDatoService;
    }

    @GetMapping
    public List<TipoDeDato> obtenerTodos() {
        return tipoDeDatoService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoDeDato> obtenerPorId(@PathVariable Long id) {
        return tipoDeDatoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TipoDeDato> crear(@RequestBody TipoDeDato tipoDeDato) {
        return ResponseEntity.ok(tipoDeDatoService.crear(tipoDeDato));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoDeDato> actualizar(@PathVariable Long id, @RequestBody TipoDeDato nuevo) {
        try {
            return ResponseEntity.ok(tipoDeDatoService.actualizar(id, nuevo));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        tipoDeDatoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
