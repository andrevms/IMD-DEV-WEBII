package com.miniprojeto2.miniprojeto2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

	@GetMapping("/")
    public String showPage(){
		return "main-menu";
	}


}
