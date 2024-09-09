package com.testertest.tester.database.repositories;

import com.testertest.tester.database.entities.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TurmaRepository extends JpaRepository<Turma, Long> {
}