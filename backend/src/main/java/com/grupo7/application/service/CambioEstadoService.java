package com.grupo7.application.service;

import com.grupo7.application.entity.CambioEstado;
import com.grupo7.application.repository.CambioEstadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CambioEstadoService {

    private final CambioEstadoRepository repository;

    public CambioEstadoService(CambioEstadoRepository repository) {
        this.repository = repository;
    }

    public List<CambioEstado> findAll() {
        return repository.findAll();
    }

    public CambioEstado findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public CambioEstado save(CambioEstado cambioEstado) {
        return repository.save(cambioEstado);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
