package com.miniprojeto2.miniprojeto2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.miniprojeto2.miniprojeto2.model.Curso;
import com.miniprojeto2.miniprojeto2.service.CursoService;

@Controller
@RequestMapping("/curso")
public class CursoController {

    @Autowired
    @Qualifier("cursoServiceImpl")
    CursoService cursoService;

    @RequestMapping("/getListaCursos")
    public String showListaCursos(Model model) {
        List<Curso> cursos = cursoService.getListaCurso();
        model.addAttribute("cursos", cursos);
        return "curso/listaCursos";
    }

    @RequestMapping("/showForm")
    public String showFromCurso(Model model) {
        model.addAttribute("curso", new Curso());
        return "curso/formCurso";
    }

    @RequestMapping("/addCurso")
    public String addCurso(@ModelAttribute("curso") Curso curso, Model model) {
        
        Curso novoCurso = cursoService.salvarCurso(curso);
        model.addAttribute("curso", novoCurso);
        return "curso/paginaCurso";
    }

    @GetMapping("/deletarCurso/{id}")
    public String deletarCurso( @PathVariable Integer id, Model model) {
        Curso curso = cursoService.getCursobyId(id);
        cursoService.deletarCurso(curso);
        return "curso/deleteCurso";
    }

    @RequestMapping("/showFormUpdate/{id}")
    public String showUpdateForm(@PathVariable Integer id, Model model) {
        Curso curso = cursoService.getCursobyId(id);
        model.addAttribute("curso", curso);
        return "curso/formUpdateCurso";
    }

    @RequestMapping("/updateCurso")
    public String updateCurso (@ModelAttribute("curso") Curso curso, Model model) {
        Curso cursoUpdate = cursoService.getCursobyId(curso.getId());
        cursoUpdate.setDescricao(curso.getDescricao());
        cursoService.salvarCurso(cursoUpdate);
        model.addAttribute("curso",  cursoUpdate );
        return "curso/paginaCurso";
    }
    
}
