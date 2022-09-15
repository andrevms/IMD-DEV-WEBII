package com.project.imd.dev.webII.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.imd.dev.webII.model.Curso;
import com.project.imd.dev.webII.service.CursoService;



@Controller
@RequestMapping("/curso")
public class CursoController {

    private int countIdCursos = 1;

    @Autowired
    @Qualifier("cursoServiceImpl")
    CursoService cursoService;

    @RequestMapping("/showForm")
    public String showFormCurso(Model model){
        model.addAttribute("curso", new Curso());
        return "curso/formCurso";
    } 

    @RequestMapping("/addCurso")
    public String showFormCurso(@ModelAttribute("curso") Curso curso,  Model model){
        curso.setId(countIdCursos);
        countIdCursos++;
        cursoService.salvarCurso(curso);
        model.addAttribute("curso", curso);
        return "curso/paginaCurso";
    }

    @RequestMapping("/getListaCursos")
    public String showListaCursos(Model model){

        List<Curso> cursos = cursoService.getListaCurso();
        model.addAttribute("cursos", cursos);
        return "curso/listaCursos";

    }

    @RequestMapping("/ListarCursos")
    public List<Curso> getListaCursos(){
        List<Curso> cursos = cursoService.getListaCurso();
        return cursos;
    }
}
