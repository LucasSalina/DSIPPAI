package com.grupo7.application.service;

import com.grupo7.application.entity.MagnitudRitcher;
import com.grupo7.application.repository.MagnitudRitcherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MagnitudRitcherService {

    private final MagnitudRitcherRepository magnitudRitcherRepository;

    @Autowired
    public MagnitudRitcherService(MagnitudRitcherRepository magnitudRitcherRepository) {
        this.magnitudRitcherRepository = magnitudRitcherRepository;
    }

    public List<MagnitudRitcher> obtenerTodos() {
        return magnitudRitcherRepository.findAll();
    }

    public Optional<MagnitudRitcher> obtenerPorId(Long id) {
        return magnitudRitcherRepository.findById(id);
    }

    public MagnitudRitcher guardar(MagnitudRitcher magnitud) {
        return magnitudRitcherRepository.save(magnitud);
    }

    public void eliminar(Long id) {
        magnitudRitcherRepository.deleteById(id);
    }
}
