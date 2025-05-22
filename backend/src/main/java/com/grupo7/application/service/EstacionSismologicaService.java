package com.grupo7.application.service;

import com.grupo7.application.entity.EstacionSismologica;
import com.grupo7.application.repository.EstacionSismologicaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstacionSismologicaService {

    private final EstacionSismologicaRepository repository;

    public EstacionSismologicaService(EstacionSismologicaRepository repository) {
        this.repository = repository;
    }

    public List<EstacionSismologica> findAll() {
        return repository.findAll();
    }

    public EstacionSismologica findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public EstacionSismologica save(EstacionSismologica estacion) {
        return repository.save(estacion);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
