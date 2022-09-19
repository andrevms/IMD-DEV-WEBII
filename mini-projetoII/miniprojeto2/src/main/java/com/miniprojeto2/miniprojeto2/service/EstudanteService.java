package com.miniprojeto2.miniprojeto2.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.miniprojeto2.miniprojeto2.model.Estudante;

@Service
public interface EstudanteService {

    public Estudante salvarEstudante(Estudante estudante);

    public void deletarEstudante(Estudante estudante);

    public Estudante getEstudanteById(Integer id);

    public List<Estudante> getListaEstudante();

}
