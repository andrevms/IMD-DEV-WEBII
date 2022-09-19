package com.miniprojeto2.miniprojeto2.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.miniprojeto2.miniprojeto2.model.Curso;

@Service
public interface CursoService {

    public Curso salvarCurso(Curso curso);

    public void deletarCurso(Curso curso);

    public Curso getCursobyId(Integer id);

    public List<Curso> getListaCurso();

}