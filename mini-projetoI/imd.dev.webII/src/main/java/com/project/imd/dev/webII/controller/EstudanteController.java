package com.project.imd.dev.webII.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.imd.dev.webII.model.Estudante;
import com.project.imd.dev.webII.service.CursoService;
import com.project.imd.dev.webII.service.EstudanteService;

@Controller
@RequestMapping("/estudante")
public class EstudanteController {

    private int countIdEstudante = 1;

    @Autowired
    @Qualifier("estudanteServiceImpl")
    EstudanteService estudanteService;

    @Autowired
    @Qualifier("cursoServiceImpl")
    CursoService cursoService;

    @RequestMapping("/showForm")
    public String showFormEstudante(Model model) {
        model.addAttribute("estudante", new Estudante());
        model.addAttribute("cursos", cursoService.getListaCurso());
        return "estudante/formEstudante";
    }

    @RequestMapping("/addEstudante")
    public String showFormEstudante(@ModelAttribute("estudante") Estudante estudante, Model model) {
        estudante.setId(countIdEstudante);
        countIdEstudante++;
        estudanteService.salvarEstudante(estudante);
        model.addAttribute("estudante", estudante);
        return "estudante/paginaEstudante";
    }

    @RequestMapping("/getDetalhesEstudante")
    public String showDetalhesEstudante(@ModelAttribute("id") Integer id, Model model) {
        Estudante estudante = estudanteService.getEstudanteById(id);
        model.addAttribute("estudante", estudante);
        return "estudante/paginaEstudante";
    }

    @RequestMapping("/getListaEstudantes")
    public String showListaEstudante(Model model) {

        List<Estudante> estudantes = estudanteService.getListaEstudante();
        model.addAttribute("estudantes", estudantes);
        return "estudante/listaEstudantes";

    }

    @RequestMapping("/getEstudantesPorCurso")
    public String showEstudantesPorCurso(@ModelAttribute("nomeCurso") String nomeCurso, Model model) {
        System.out.println(nomeCurso);
        List<Estudante> estudantes = estudanteService.getListaEstudantePorCurso(nomeCurso);
        model.addAttribute("estudantes", estudantes);
        return "estudante/listaEstudantes";

    }

    @RequestMapping("/getEstudantesPorLinguagem")
    public String showEstudantesPorLinguagem(@ModelAttribute("linguagem") String linguagem, Model model) {
        List<Estudante> estudantes = estudanteService.getListaEstudantePorLinguagem(linguagem);
        model.addAttribute("estudantes", estudantes);
        return "estudante/listaEstudantes";

    }

    @RequestMapping("/removeEstudante")
    public String removeEstudante(@ModelAttribute("id") Integer id, Model model) {
        Estudante estudante = estudanteService.getEstudanteById(id);
        estudanteService.deletarEstudante(estudante);
        return "estudante/listaEstudantes";

    }

    @RequestMapping("/estudante")
    public String getEstudantePeloID(@ModelAttribute("id") Integer id, Model model) {

        Estudante estudante = estudanteService.getEstudanteById(id);
        model.addAttribute("estudante", estudante);
        return "estudante/paginaEstudante";

    }

}
