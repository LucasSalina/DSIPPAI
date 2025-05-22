package com.grupo7.application.service;

import com.grupo7.application.entity.ClasificacionSismo;
import com.grupo7.application.repository.ClasificacionSismoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClasificacionSismoService {

    private final ClasificacionSismoRepository repository;

    public ClasificacionSismoService(ClasificacionSismoRepository repository) {
        this.repository = repository;
    }

    public List<ClasificacionSismo> findAll() {
        return repository.findAll();
    }

    public ClasificacionSismo findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public ClasificacionSismo save(ClasificacionSismo entity) {
        return repository.save(entity);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
