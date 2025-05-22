package com.grupo7.application.service;

import com.grupo7.application.entity.SerieTemporal;
import com.grupo7.application.repository.SerieTemporalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SerieTemporalService {

    private final SerieTemporalRepository serieTemporalRepository;

    @Autowired
    public SerieTemporalService(SerieTemporalRepository serieTemporalRepository) {
        this.serieTemporalRepository = serieTemporalRepository;
    }

    public List<SerieTemporal> obtenerTodas() {
        return serieTemporalRepository.findAll();
    }

    public Optional<SerieTemporal> obtenerPorId(Long id) {
        return serieTemporalRepository.findById(id);
    }

    public SerieTemporal guardar(SerieTemporal serieTemporal) {
        return serieTemporalRepository.save(serieTemporal);
    }

    public void eliminar(Long id) {
        serieTemporalRepository.deleteById(id);
    }
}
