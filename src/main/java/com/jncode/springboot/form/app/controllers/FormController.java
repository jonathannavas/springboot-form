package com.jncode.springboot.form.app.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.jncode.springboot.form.app.models.domain.Usuario;

@Controller
@SessionAttributes("usuario")
public class FormController {

	@GetMapping("/form")
	public String form(Model model) {
		
		Usuario usuario = new Usuario();
		
		model.addAttribute("titulo", "Form");
		
		usuario.setNombre("Jonathan");
		usuario.setApellido("Navas");
		
		usuario.setIdentificador("123.456.789-K");
		
		model.addAttribute("usuario", usuario);
		
		return "form";
	}
	
	
	@PostMapping("/form")
	public String procesar(@Valid Usuario usuario, BindingResult result, Model model, SessionStatus status) {
		
		model.addAttribute("titulo", "Form results");
		
		if(result.hasErrors()) {
			return "form";
		}
		
		model.addAttribute("usuario", usuario);
		
		status.setComplete();
		
		return "resultado";
	}
	
}
