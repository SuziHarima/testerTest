package com.testertest.tester.service;

import com.testertest.tester.database.entities.Estudante;
import com.testertest.tester.database.entities.Turma;
import com.testertest.tester.database.repositories.TurmaRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class TurmaServiceTest {

    @Mock
    TurmaRepository turmaRepository;

    @InjectMocks
    TurmaService turmaService;

    static Turma turma;

    @BeforeAll
    public static void setup(){
        turma = new Turma(1L,
                "turma",
                Collections.emptyList());
    }

    @Test
    void cadastrarTurma() {
        when(turmaRepository.save(any())).thenReturn(turma);

        Turma retorno = turmaService.cadastrarTurma(turma.getNome());

        verify(turmaRepository, times(1)).save(any(Turma.class));
        assertNotNull(retorno);
        assertEquals(turma.getNome(), retorno.getNome());
    }

    @Test
    void listarTurmas() {
        when(turmaRepository.findAll()).thenReturn(List.of(turma));

        List<Turma> retorno = turmaService.listarTurmas();

        verify(turmaRepository).findAll();
        assertNotNull(retorno);
        assertEquals(turma.getNome(), retorno.get(0).getNome());
    }

    @Test
    void buscarTurmaPorId() {
        when(turmaRepository.findById(anyLong()))
                .thenReturn(Optional.ofNullable(turma));

        Turma retorno = turmaService.buscarTurmaPorId(1L);

        verify(turmaRepository).findById(anyLong());
        assertEquals(turma.getNome(), retorno.getNome());
    }

    @Test
    void atualizarTurma() {
        when(turmaRepository.findById(anyLong()))
                .thenReturn(Optional.of(turma));
        when(turmaRepository.save(any(Turma.class))).thenReturn(turma);

        Turma retorno = turmaService.atualizarTurma(2L, "turma alterada");

        verify(turmaRepository).findById(anyLong());
        verify(turmaRepository).save(any(Turma.class));
        assertNotNull(retorno);
        assertEquals("turma alterada", retorno.getNome());
    }

    @Test
    void removerTurma() {
    }

    @Test
    void adicionarEstudanteNaTurma() {
    }

    @Test
    void removerEstudanteDaTurma() {
    }
}