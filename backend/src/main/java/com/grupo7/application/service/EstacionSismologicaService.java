package com.grupo7.application.service;

import com.grupo7.application.entity.EstacionSismologica;
import com.grupo7.application.repository.EstacionSismologicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstacionSismologicaService {
    private final EstacionSismologicaRepository estacionSismologicaRepository;

    @Autowired
    public EstacionSismologicaService(EstacionSismologicaRepository estacionSismologicaRepository) {
        this.estacionSismologicaRepository = estacionSismologicaRepository;
    }

    public List<EstacionSismologica> findAll() {
        return estacionSismologicaRepository.findAll();
    }

    public Optional<EstacionSismologica> findById(String id) {
        return estacionSismologicaRepository.findById(id);
    }

    public EstacionSismologica save(EstacionSismologica estacion) {
        return estacionSismologicaRepository.save(estacion);
    }

    public void deleteById(String id) {
        estacionSismologicaRepository.deleteById(id);
    }
}
