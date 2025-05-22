package com.grupo7.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.grupo7.application.entity.MagnitudRitcher;

@Repository
public interface MagnitudRitcherRepository extends JpaRepository<MagnitudRitcher, Long> {
}
