
package com.grupo7.application.service;

import com.grupo7.application.entity.MuestraSismica;
import com.grupo7.application.repository.MuestraSismicaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MuestraSismicaService {

    private final MuestraSismicaRepository repository;

    public MuestraSismicaService(MuestraSismicaRepository repository) {
        this.repository = repository;
    }

    public List<MuestraSismica> findAll() {
        return repository.findAll();
    }

    public Optional<MuestraSismica> findById(Long id) {
        return repository.findById(id);
    }

    public MuestraSismica save(MuestraSismica muestra) {
        return repository.save(muestra);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}

