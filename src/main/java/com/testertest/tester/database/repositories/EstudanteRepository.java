package com.testertest.tester.database.repositories;

import com.testertest.tester.database.entities.Estudante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstudanteRepository extends JpaRepository<Estudante, Long> {
}