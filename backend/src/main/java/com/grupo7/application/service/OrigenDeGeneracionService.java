package com.grupo7.application.service;

import com.grupo7.application.entity.OrigenDeGeneracion;
import com.grupo7.application.repository.OrigenDeGeneracionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrigenDeGeneracionService {

    @Autowired
    private OrigenDeGeneracionRepository repository;

    public List<OrigenDeGeneracion> findAll() {
        return repository.findAll();
    }

    public Optional<OrigenDeGeneracion> findById(Long id) {
        return repository.findById(id);
    }

    public OrigenDeGeneracion save(OrigenDeGeneracion origen) {
        return repository.save(origen);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
