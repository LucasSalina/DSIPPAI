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

    public SerieTemporal crear(SerieTemporal serieTemporal) {
        return serieTemporalRepository.save(serieTemporal);
    }

    public SerieTemporal actualizar(Long id, SerieTemporal actualizada) {
        return serieTemporalRepository.findById(id)
                .map(serieExistente -> {
                    // Actualiza los campos relevantes
                    serieExistente.setCondicionAlarma(actualizada.getCondicionAlarma());
                    serieExistente.setFechaHoraInicioRegistroMuestra(actualizada.getFechaHoraInicioRegistroMuestra());
                    serieExistente.setFechaHoraRegistro(actualizada.getFechaHoraRegistro());
                    serieExistente.setFrecuenciaMuestreo(actualizada.getFrecuenciaMuestreo());
                    serieExistente.setMuestrasSismicas(actualizada.getMuestrasSismicas());
                    return serieTemporalRepository.save(serieExistente);
                })
                .orElseThrow(() -> new RuntimeException("SerieTemporal no encontrada con ID: " + id));
    }

    public void eliminar(Long id) {
        serieTemporalRepository.deleteById(id);
    }

    public List<Object> obtenerDatosDeSerie(SerieTemporal serieTemporal) {
        return serieTemporal.getDatos();
    }
}
