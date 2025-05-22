package com.grupo7.application.service;

import com.grupo7.application.entity.TipoDeDato;
import com.grupo7.application.repository.TipoDeDatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoDeDatoService {
    private final TipoDeDatoRepository tipoDeDatoRepository;

    @Autowired
    public TipoDeDatoService(TipoDeDatoRepository tipoDeDatoRepository) {
        this.tipoDeDatoRepository = tipoDeDatoRepository;
    }

    public List<TipoDeDato> findAll() {
        return tipoDeDatoRepository.findAll();
    }

    public Optional<TipoDeDato> findById(Long id) {
        return tipoDeDatoRepository.findById(id);
    }

    public TipoDeDato save(TipoDeDato tipo) {
        return tipoDeDatoRepository.save(tipo);
    }

    public void deleteById(Long id) {
        tipoDeDatoRepository.deleteById(id);
    }
}
