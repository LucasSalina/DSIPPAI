package com.grupo7.application.service;

import com.grupo7.application.entity.DetalleMuestraSismica;
import com.grupo7.application.repository.DetalleMuestraSismicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetalleMuestraSismicaService {
    private final DetalleMuestraSismicaRepository detalleMuestraSismicaRepository;

    @Autowired
    public DetalleMuestraSismicaService(DetalleMuestraSismicaRepository detalleMuestraSismicaRepository) {
        this.detalleMuestraSismicaRepository = detalleMuestraSismicaRepository;
    }

    public List<DetalleMuestraSismica> findAll() {
        return detalleMuestraSismicaRepository.findAll();
    }

    public Optional<DetalleMuestraSismica> findById(Long id) {
        return detalleMuestraSismicaRepository.findById(id);
    }

    public DetalleMuestraSismica save(DetalleMuestraSismica detalle) {
        return detalleMuestraSismicaRepository.save(detalle);
    }

    public void deleteById(Long id) {
        detalleMuestraSismicaRepository.deleteById(id);
    }
}
