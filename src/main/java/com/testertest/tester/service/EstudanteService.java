package com.testertest.tester.service;

import com.testertest.tester.database.entities.Estudante;
import com.testertest.tester.database.repositories.EstudanteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstudanteService {

    final EstudanteRepository estudanteRepository;

    public Estudante cadastrarEstudante(String nome, String matricula) {
        Estudante estudante = new Estudante();
        estudante.setNome(nome);
        estudante.setMatricula(matricula);
        estudanteRepository.save(estudante);
        return estudante;
    }

    public List<Estudante> listarEstudantes() {
        return estudanteRepository.findAll();
    }

    public Estudante buscarEstudantePorId(Long id) {
        return estudanteRepository.findById(id)
                .orElseThrow(
                    () ->{
                        throw new RuntimeException("Estudante não encontrado");
                    }
        );
    }

    public Estudante atualizarEstudante(Long id, String novoNome, String novaMatricula) {
        Estudante estudante = buscarEstudantePorId(id);
        estudante.setNome(novoNome);
        estudante.setMatricula(novaMatricula);
        estudanteRepository.save(estudante);
        return estudante;
    }

    public void removerEstudante(Long id) {
        estudanteRepository.deleteById(id);
    }
}
