package com.testertest.tester.controller;

import com.testertest.tester.database.entities.Estudante;
import com.testertest.tester.service.EstudanteService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class EstudanteControllerTest {

    @MockBean
    EstudanteService estudanteService;

    @Autowired
    MockMvc mockMvc;

    static Estudante estudante;

    @BeforeAll
    public static void setup() {
        estudante = new Estudante(1L,
                "Salem",
                "123.456.78",
                new ArrayList<>());
    }

    @Test
    void listarEstudantes() throws Exception{
//        when(estudanteService.listarEstudantes()).thenReturn(Collections.emptyList());

        mockMvc.perform(
                get("/estudantes")
        ).andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    void buscarEstudantePorId() throws Exception {
        when(estudanteService.buscarEstudantePorId(anyLong())).thenReturn(estudante);

        mockMvc.perform(
                        get("/estudantes/1")
                ).andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value(estudante.getNome()));
    }

    @Test
    void cadastrarEstudante() throws Exception {

        mockMvc.perform(
                post("/estudantes")
                        .content("{\n" +
                                "\t\"nome\":\"Haley\",\n" +
                                "\t\"matricula\": \"112.113.114\"\n" +
                                "}")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    void atualizarEstudante() throws Exception {
        mockMvc.perform(
                put("/estudantes/1")
                        .content("{\n" +
                                "\t\"nome\":\"Biscoito\",\n" +
                                "\t\"matricula\": \"112.113.114\"\n" +
                                "}")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    void removerEstudante() throws Exception {
        mockMvc.perform(
                delete("/estudantes/1")
        ).andExpect(status().isNoContent());
    }
}