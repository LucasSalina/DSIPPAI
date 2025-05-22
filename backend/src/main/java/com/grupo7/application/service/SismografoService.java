package com.grupo7.application.service;

import com.grupo7.application.entity.Sismografo;
import com.grupo7.application.repository.SismografoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SismografoService {
    private final SismografoRepository sismografoRepository;

    @Autowired
    public SismografoService(SismografoRepository sismografoRepository) {
        this.sismografoRepository = sismografoRepository;
    }

    public List<Sismografo> findAll() {
        return sismografoRepository.findAll();
    }

    public Optional<Sismografo> findById(String id) {
        return sismografoRepository.findById(id);
    }

    public Sismografo save(Sismografo sismografo) {
        return sismografoRepository.save(sismografo);
    }

    public void deleteById(String id) {
        sismografoRepository.deleteById(id);
    }
}
