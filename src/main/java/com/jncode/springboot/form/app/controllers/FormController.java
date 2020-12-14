package com.jncode.springboot.form.app.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.jncode.springboot.form.app.models.domain.Usuario;

@Controller
public class FormController {

	@GetMapping("/form")
	public String form(Model model) {
		
		Usuario usuario = new Usuario();
		
		model.addAttribute("titulo", "Form");
		model.addAttribute("usuario", usuario);
		
		return "form";
	}
	
	
	@PostMapping("/form")
	public String procesar(@Valid Usuario user, BindingResult result, Model model) {
		
		model.addAttribute("titulo", "Form results");
		
		if(result.hasErrors()) {
			
			Map<String, String> errores = new HashMap<>(); 
			
			result.getFieldErrors().forEach(err->{ 
				errores.put(err.getField(), "El campo ".concat(err.getField()).concat(" ".concat(err.getDefaultMessage())));
			});
			
			model.addAttribute("error", errores);
			
			return "form";
		}
		
		model.addAttribute("user", user);
		
		return "resultado";
	}
	
}
