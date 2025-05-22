package com.grupo7.application.service;

import com.grupo7.application.entity.ClasificacionSismo;
import com.grupo7.application.repository.ClasificacionSismoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClasificacionSismoService {
    private final ClasificacionSismoRepository clasificacionSismoRepository;

    @Autowired
    public ClasificacionSismoService(ClasificacionSismoRepository clasificacionSismoRepository) {
        this.clasificacionSismoRepository = clasificacionSismoRepository;
    }

    public List<ClasificacionSismo> findAll() {
        return clasificacionSismoRepository.findAll();
    }

    public Optional<ClasificacionSismo> findById(Long id) {
        return clasificacionSismoRepository.findById(id);
    }

    public ClasificacionSismo save(ClasificacionSismo clasificacion) {
        return clasificacionSismoRepository.save(clasificacion);
    }

    public void deleteById(Long id) {
        clasificacionSismoRepository.deleteById(id);
    }
}
