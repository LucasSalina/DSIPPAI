package com.grupo7.application.service;

import com.grupo7.application.entity.TipoDeDato;
import com.grupo7.application.repository.TipoDeDatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoDeDatoService {

    private final TipoDeDatoRepository tipoDeDatoRepository;

    @Autowired
    public TipoDeDatoService(TipoDeDatoRepository tipoDeDatoRepository) {
        this.tipoDeDatoRepository = tipoDeDatoRepository;
    }

    public List<TipoDeDato> obtenerTodos() {
        return tipoDeDatoRepository.findAll();
    }

    public Optional<TipoDeDato> obtenerPorId(Long id) {
        return tipoDeDatoRepository.findById(id);
    }

    public TipoDeDato crear(TipoDeDato tipoDeDato) {
        return tipoDeDatoRepository.save(tipoDeDato);
    }

    public TipoDeDato actualizar(Long id, TipoDeDato datosNuevos) {
        return tipoDeDatoRepository.findById(id).map(td -> {
            td = new TipoDeDato(
                datosNuevos.getDenominacion(),
                datosNuevos.getNombreUnidadMedida(),
                datosNuevos.getValorUmbral()
            );
            return tipoDeDatoRepository.save(td);
        }).orElseThrow(() -> new RuntimeException("TipoDeDato no encontrado con id: " + id));
    }

    public void eliminar(Long id) {
        tipoDeDatoRepository.deleteById(id);
    }
}
