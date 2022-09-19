package com.miniprojeto2.miniprojeto2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.miniprojeto2.miniprojeto2.model.Curso;
import com.miniprojeto2.miniprojeto2.repository.CursoRepository;

@Component
public class CursoServiceImpl implements CursoService {

    @Autowired
    CursoRepository cursoRepository;

    @Override
    public Curso salvarCurso(Curso curso) {
        return cursoRepository.save(curso);
    }

    @Override
    public void deletarCurso(Curso curso) {
        cursoRepository.delete(curso);
    }

    @Override
    public Curso getCursobyId(Integer id) {
        return cursoRepository.findById(id).map(curso -> {
            return curso;
        }).orElseThrow(() -> null);
    }

    @Override
    public List<Curso> getListaCurso() {
        return cursoRepository.findAll();
    }

}
