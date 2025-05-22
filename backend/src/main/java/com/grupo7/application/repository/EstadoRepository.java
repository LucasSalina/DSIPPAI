package com.grupo7.application.repository;

import com.grupo7.application.entity.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long> {
    Optional<Estado> findByAmbitoAndNombreEstado(String ambito, String nombreEstado);
}