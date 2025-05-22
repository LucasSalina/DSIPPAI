package com.grupo7.application.service;

import com.grupo7.application.entity.Estado;
import com.grupo7.application.repository.EstadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoService {

    private final EstadoRepository repository;

    public EstadoService(EstadoRepository repository) {
        this.repository = repository;
    }

    public List<Estado> findAll() {
        return repository.findAll();
    }

    public Estado findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Estado save(Estado estado) {
        return repository.save(estado);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
