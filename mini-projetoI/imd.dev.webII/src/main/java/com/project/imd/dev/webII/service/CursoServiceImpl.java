package com.project.imd.dev.webII.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.project.imd.dev.webII.model.Curso;

@Component
public class CursoServiceImpl implements CursoService {

    private static List<Curso> cursos = new ArrayList<Curso>();

    @Override
    public void salvarCurso(Curso curso) {
        System.out.println(curso.toString());
        try {
            cursos.add(curso);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.toString());
        }

    }

    @Override
    public void deletarCurso(Curso curso) {
        cursos.remove(curso);
    }

    @Override
    public Curso getCursoById(Integer id) {
        for (Curso curso : cursos) {
            if (curso.getId() == id) {
                return curso;
            }
        }
        return null;
    }

    @Override
    public List<Curso> getListaCurso() {
        return cursos;
    }

    @Override
    public List<Curso> getListaCursoPorLinguagem(String linguagem) {

        List<Curso> CursoPorLinguagem = new ArrayList<Curso>();

        for (Curso curso : cursos) {
            if (curso.getLinguagem() == linguagem) {
                CursoPorLinguagem.add(curso);
            }
        }
        return CursoPorLinguagem;
    }

}
