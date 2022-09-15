package com.project.imd.dev.webII.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.imd.dev.webII.model.Curso;

@Service
public interface CursoService {
    public void salvarCurso(Curso Curso);
    public void deletarCurso(Curso Curso);
    public Curso getCursoById(Integer id);
    public List<Curso> getListaCurso();
    public List<Curso> getListaCursoPorLinguagem(String linguagem);
}

