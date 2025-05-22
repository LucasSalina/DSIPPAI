package com.grupo7.application.service;

import com.grupo7.application.entity.DetalleMuestraSismica;
import com.grupo7.application.repository.DetalleMuestraSismicaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetalleMuestraSismicaService {

    private final DetalleMuestraSismicaRepository repository;

    public DetalleMuestraSismicaService(DetalleMuestraSismicaRepository repository) {
        this.repository = repository;
    }

    public List<DetalleMuestraSismica> findAll() {
        return repository.findAll();
    }

    public DetalleMuestraSismica findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public DetalleMuestraSismica save(DetalleMuestraSismica entity) {
        return repository.save(entity);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
