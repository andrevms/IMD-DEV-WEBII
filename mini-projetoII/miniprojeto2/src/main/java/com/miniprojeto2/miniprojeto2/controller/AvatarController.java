package com.miniprojeto2.miniprojeto2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.miniprojeto2.miniprojeto2.model.Avatar;
import com.miniprojeto2.miniprojeto2.model.Estudante;
import com.miniprojeto2.miniprojeto2.service.AvatarService;
import com.miniprojeto2.miniprojeto2.service.EstudanteService;

@Controller
@RequestMapping("/avatar")
public class AvatarController {

    @Autowired
    @Qualifier("avatarServiceImpl")
    AvatarService avatarService;

    @Autowired
    @Qualifier("estudanteServiceImpl")
    EstudanteService estudanteService;

    @RequestMapping("/getListaAvatar")
    public String showListaCursos(Model model) {
        List<Avatar> avatarList = avatarService.getListaAvatar();

        model.addAttribute("avatares", avatarList);
        return "avatar/listaAvatar";
    }

    @RequestMapping("/showForm")
    public String showFromAvatar(Model model) {
        List<Estudante> estudantes = estudanteService.getListaEstudante();

        model.addAttribute("avatar", new Avatar());
        model.addAttribute("estudantes", estudantes);
        return "avatar/formAvatar";
    }

    @RequestMapping("/addAvatar")
    public String addAvatar(@ModelAttribute("avatar") Avatar avatar, Model model) {
        avatarService.salvarAvatar(avatar);

        List<Avatar> avatarList = avatarService.getListaAvatar();
        model.addAttribute("avatares", avatarList);
        return "avatar/listaAvatar";
    }

    @RequestMapping("/deleteAvatar/{id}")
    public String deleteAvatar( @PathVariable String id, Model model) {
        Avatar avatar = avatarService.getAvatarbyId(id);
        avatarService.deletarAvatar(avatar);

        List<Avatar> avatarList = avatarService.getListaAvatar();
        model.addAttribute("avatares", avatarList);
        return "avatar/listaAvatar";
    }

}
