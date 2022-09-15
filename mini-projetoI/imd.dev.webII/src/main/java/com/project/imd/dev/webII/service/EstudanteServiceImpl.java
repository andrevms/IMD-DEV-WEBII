package com.project.imd.dev.webII.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.project.imd.dev.webII.model.Estudante;

@Component
public class EstudanteServiceImpl implements EstudanteService {

    public List<Estudante> estudantes = new ArrayList<Estudante>();

    @Override
    public void salvarEstudante(Estudante estudante) {
        System.out.println(estudante.toString());
        try {
            this.estudantes.add(estudante);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.toString());
        }
    }

    @Override
    public void deletarEstudante(Estudante estudante) {
        this.estudantes.remove(estudante);
    }

    @Override
    public Estudante getEstudanteById(Integer id) {
        for (Estudante estudante : estudantes) {
            if (estudante.getId() == id) {
                return estudante;
            }
        }
        return null;
    }

    @Override
    public List<Estudante> getListaEstudante() {
        return this.estudantes;
    }

    @Override
    public List<Estudante> getListaEstudantePorCurso(String curso) {
        List<Estudante> estudantesDoCurso = new ArrayList<Estudante>();

        for (Estudante estudante : estudantes) {
            if (estudante.getCurso().equals(curso)) {
                estudantesDoCurso.add(estudante);
            }
        }
        return estudantesDoCurso;
    }

    @Override
    public List<Estudante> getListaEstudantePorLinguagem(String linguagem) {
        List<Estudante> estudantesDaLinguagem = new ArrayList<Estudante>();

        for (Estudante estudante : estudantes) {
            if (estudante.getLinguagem().equals(linguagem)) {
                estudantesDaLinguagem.add(estudante);
            }
        }
        return estudantesDaLinguagem;
    }

}
