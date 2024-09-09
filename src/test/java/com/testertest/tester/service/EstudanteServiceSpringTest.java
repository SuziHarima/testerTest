package com.testertest.tester.service;

import com.testertest.tester.database.entities.Estudante;
import com.testertest.tester.database.repositories.EstudanteRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class EstudanteServiceSpringTest {

    @MockBean
    EstudanteRepository estudanteRepository;

    @Autowired
    EstudanteService estudanteService;

    static Estudante estudante;

    @BeforeAll
    public static void setup() {
        estudante = new Estudante(1L,
                "Salem",
                "123.456.78",
                new ArrayList<>());
    }

    @Test
    void cadastrarEstudante() {
        when(estudanteRepository.save(any())).thenReturn(estudante);

        Estudante retorno = estudanteService
                .cadastrarEstudante(estudante.getNome(),
                                    estudante.getMatricula());

        assertNotNull(retorno);
        assertEquals(estudante.getNome(), retorno.getNome());

        verify(estudanteRepository, times(1)).save(any());

    }

    @Test
    void listarEstudantes() {

        when(estudanteRepository.findAll()).thenReturn(List.of(estudante));

        List<Estudante> retorno = estudanteService.listarEstudantes();

        assertNotNull(retorno);
        assertEquals(estudante.getNome(), retorno.get(0).getNome());

    }

    @Test
    void buscarEstudantePorId() {
        when(estudanteRepository.findById(anyLong()))
                .thenReturn(Optional.ofNullable(estudante));

        assertDoesNotThrow(
                () -> estudanteService.buscarEstudantePorId(1L)
        );
    }

    @Test
    void atualizarEstudante() {
        when(estudanteRepository.findById(anyLong()))
                .thenReturn(Optional.of(estudante));
        when(estudanteRepository.save(any(Estudante.class))).thenReturn(estudante);

        Estudante retorno = estudanteService.atualizarEstudante(1L,
                "Kirara",
                "876.543.21");


        verify(estudanteRepository).findById(anyLong());
        verify(estudanteRepository).save(any(Estudante.class));
        assertNotNull(retorno);
        assertEquals("Kirara", retorno.getNome());
    }

    @Test
    void removerEstudante() {
        estudanteService.removerEstudante(1L);

        verify(estudanteRepository).deleteById(anyLong());
    }
}