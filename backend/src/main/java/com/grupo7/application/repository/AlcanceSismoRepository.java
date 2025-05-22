package com.grupo7.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.grupo7.application.entity.AlcanceSismo;

@Repository
public interface AlcanceSismoRepository extends JpaRepository<AlcanceSismo, Long> {
}
