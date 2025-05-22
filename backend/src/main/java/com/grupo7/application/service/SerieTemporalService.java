package com.grupo7.application.service;

import com.grupo7.application.entity.SerieTemporal;
import com.grupo7.application.entity.MuestraSismica; // Import MuestraSismica if not already present
import com.grupo7.application.repository.SerieTemporalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList; // Potentially needed if you reconstruct a list of data

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

    // FIX: Changed ID type from Long to Integer to match SerieTemporal entity's ID_SERIE
    public Optional<SerieTemporal> obtenerPorId(Integer id) {
        return serieTemporalRepository.findById(id);
    }

    public SerieTemporal crear(SerieTemporal serieTemporal) {
        return serieTemporalRepository.save(serieTemporal);
    }

    // FIX: Changed ID type from Long to Integer
    public SerieTemporal actualizar(Integer id, SerieTemporal actualizada) {
        return serieTemporalRepository.findById(id)
                .map(serieExistente -> {
                    // Actualiza los campos relevantes
                    serieExistente.setCondicionAlarma(actualizada.getCondicionAlarma());
                    // FIX: Corrected method name as per SerieTemporal entity
                    serieExistente.setFechaHoraInicioRegMuestreo(actualizada.getFechaHoraInicioRegMuestreo());
                    // FIX: Corrected method name as per SerieTemporal entity
                    serieExistente.setFechaHoraRegistros(actualizada.getFechaHoraRegistros());
                    serieExistente.setFrecuenciaMuestreo(actualizada.getFrecuenciaMuestreo());

                    // FIX: SerieTemporal now has a ManyToOne relationship with MuestraSismica.
                    // It sets a single MuestraSismica object, not a list.
                    serieExistente.setMuestraSismica(actualizada.getMuestraSismica());
                    // You might also need to set the Sismografo and EventoSismico if they can be updated this way
                    serieExistente.setSismografo(actualizada.getSismografo());
                    serieExistente.setEventoSismico(actualizada.getEventoSismico());

                    return serieTemporalRepository.save(serieExistente);
                })
                .orElseThrow(() -> new RuntimeException("SerieTemporal no encontrada con ID: " + id));
    }

    // FIX: Changed ID type from Long to Integer
    public void eliminar(Integer id) {
        serieTemporalRepository.deleteById(id);
    }

    // FIX: Re-implemented this method.
    // SerieTemporal no longer has a getDatos() method directly.
    // It now has a ManyToOne relationship with MuestraSismica.
    // This assumes MuestraSismica has a getDatos() method that returns the relevant data.
    public Object obtenerDatosDeSerie(Integer serieTemporalId) { // Changed input to ID for service call
        Optional<SerieTemporal> serieTemporalOptional = serieTemporalRepository.findById(serieTemporalId);
        if (serieTemporalOptional.isPresent()) {
            SerieTemporal serieTemporal = serieTemporalOptional.get();
            MuestraSismica muestraSismica = serieTemporal.getMuestraSismica();
            if (muestraSismica != null) {
                // Assuming MuestraSismica.getDatos() returns an Object (e.g., a DTO, or a simple value)
                return muestraSismica.getDatos();
            }
        }
        return null; // Or throw an exception if no data is found or MuestraSismica is null
    }
}