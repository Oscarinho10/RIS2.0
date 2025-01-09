package com.example.RIS.turno.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurnoRespository extends JpaRepository<Turno, Long> {}
