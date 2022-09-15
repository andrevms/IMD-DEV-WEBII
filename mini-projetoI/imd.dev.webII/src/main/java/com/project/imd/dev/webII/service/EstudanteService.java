package com.project.imd.dev.webII.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.imd.dev.webII.model.Estudante;

@Service
public interface EstudanteService {

    public void salvarEstudante(Estudante estudante);
    public void deletarEstudante(Estudante estudante);
    public Estudante getEstudanteById(Integer id);
    public List<Estudante> getListaEstudante();
    public List<Estudante> getListaEstudantePorCurso(String curso);
    public List<Estudante> getListaEstudantePorLinguagem(String linguagem);
    
}
