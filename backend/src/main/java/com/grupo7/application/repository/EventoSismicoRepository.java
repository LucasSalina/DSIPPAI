// src/main/java/com/grupo7/application/repository/EventoSismicoRepository.java
package com.grupo7.application.repository;

import com.grupo7.application.entity.EventoSismico;
import com.grupo7.application.entity.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventoSismicoRepository extends JpaRepository<EventoSismico, Long> {
    List<EventoSismico> findByEstadoActualIn(List<Estado> estados);
}