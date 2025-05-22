package com.grupo7.application.service;

import com.grupo7.application.entity.Sismografo;
import com.grupo7.application.repository.SismografoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SismografoService {

    private final SismografoRepository repositorio;

    public SismografoService(SismografoRepository repositorio) {
        this.repositorio = repositorio;
    }

    public List<Sismografo> obtenerTodos() {
        return repositorio.findAll();
    }

    public Sismografo guardar(Sismografo sismografo) {
        return repositorio.save(sismografo);
    }

}
