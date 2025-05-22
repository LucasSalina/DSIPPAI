package com.grupo7.application.repository;

import com.grupo7.application.entity.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    // You can add custom query methods here if needed,
    // e.g., Empleado findByEmail(String email);
}