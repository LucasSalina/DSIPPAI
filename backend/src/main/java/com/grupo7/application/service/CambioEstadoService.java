package com.grupo7.application.service;

import com.grupo7.application.entity.CambioEstado;
import com.grupo7.application.repository.CambioEstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CambioEstadoService {
    private final CambioEstadoRepository cambioEstadoRepository;

    @Autowired
    public CambioEstadoService(CambioEstadoRepository cambioEstadoRepository) {
        this.cambioEstadoRepository = cambioEstadoRepository;
    }

    public List<CambioEstado> findAll() {
        return cambioEstadoRepository.findAll();
    }

    public Optional<CambioEstado> findById(Long id) {
        return cambioEstadoRepository.findById(id);
    }

    public CambioEstado save(CambioEstado cambio) {
        return cambioEstadoRepository.save(cambio);
    }

    public void deleteById(Long id) {
        cambioEstadoRepository.deleteById(id);
    }
}
