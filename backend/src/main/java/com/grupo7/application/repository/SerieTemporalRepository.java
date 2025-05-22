package com.grupo7.application.repository;

import com.grupo7.application.entity.SerieTemporal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// FIX: Changed Long to Integer for the ID type
@Repository
public interface SerieTemporalRepository extends JpaRepository<SerieTemporal, Integer> {
    // You can add custom query methods here if needed
}