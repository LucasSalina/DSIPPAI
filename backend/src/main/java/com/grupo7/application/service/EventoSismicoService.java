package com.grupo7.application.service;

import com.grupo7.application.entity.EventoSismico;
import com.grupo7.application.repository.EventoSismicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventoSismicoService {

    @Autowired
    private EventoSismicoRepository repository;

    public List<EventoSismico> findAll() {
        return repository.findAll();
    }

    public Optional<EventoSismico> findById(Long id) {
        return repository.findById(id);
    }

    public EventoSismico save(EventoSismico evento) {
        return repository.save(evento);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
