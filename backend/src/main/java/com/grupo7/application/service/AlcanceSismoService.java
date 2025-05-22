package com.grupo7.application.service;

import com.grupo7.application.entity.AlcanceSismo;
import com.grupo7.application.repository.AlcanceSismoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlcanceSismoService {
    private final AlcanceSismoRepository alcanceSismoRepository;

    @Autowired
    public AlcanceSismoService(AlcanceSismoRepository alcanceSismoRepository) {
        this.alcanceSismoRepository = alcanceSismoRepository;
    }

    public List<AlcanceSismo> findAll() {
        return alcanceSismoRepository.findAll();
    }

    public Optional<AlcanceSismo> findById(Long id) {
        return alcanceSismoRepository.findById(id);
    }

    public AlcanceSismo save(AlcanceSismo alcance) {
        return alcanceSismoRepository.save(alcance);
    }

    public void deleteById(Long id) {
        alcanceSismoRepository.deleteById(id);
    }
}
