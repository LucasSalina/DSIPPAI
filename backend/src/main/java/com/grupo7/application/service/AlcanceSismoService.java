package com.grupo7.application.service;

import com.grupo7.application.entity.AlcanceSismo;
import com.grupo7.application.repository.AlcanceSismoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlcanceSismoService {

    private final AlcanceSismoRepository repository;

    public AlcanceSismoService(AlcanceSismoRepository repository) {
        this.repository = repository;
    }

    public List<AlcanceSismo> findAll() {
        return repository.findAll();
    }

    public AlcanceSismo findById(String nombre) {
        return repository.findById(nombre).orElse(null);
    }

    public AlcanceSismo save(AlcanceSismo alcanceSismo) {
        return repository.save(alcanceSismo);
    }

    public void deleteById(String nombre) {
        repository.deleteById(nombre);
    }
}
