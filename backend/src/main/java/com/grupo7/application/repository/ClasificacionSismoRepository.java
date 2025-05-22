package com.grupo7.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.grupo7.application.entity.ClasificacionSismo;

@Repository
public interface ClasificacionSismoRepository extends JpaRepository<ClasificacionSismo, Long> {
}
