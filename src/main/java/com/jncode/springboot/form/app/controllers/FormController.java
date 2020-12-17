package com.jncode.springboot.form.app.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.jncode.springboot.form.app.editors.NombreMayusculaEditor;
import com.jncode.springboot.form.app.editors.PaisPropertyEditor;
import com.jncode.springboot.form.app.editors.RolesEditor;
import com.jncode.springboot.form.app.models.domain.Pais;
import com.jncode.springboot.form.app.models.domain.Role;
import com.jncode.springboot.form.app.models.domain.Usuario;
import com.jncode.springboot.form.app.services.IPaisService;
import com.jncode.springboot.form.app.services.IRoleService;
import com.jncode.springboot.form.app.validation.UsuarioValidador;

@Controller
@SessionAttributes("usuario")
public class FormController {

	@Autowired
	private UsuarioValidador validador;

	@Autowired
	private IPaisService paisService;

	@Autowired
	private PaisPropertyEditor paisEditor;

	@Autowired
	private IRoleService roleService;

	@Autowired
	private RolesEditor roleEditor;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(validador);

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, "fechaNacimiento", new CustomDateEditor(dateFormat, false));

		binder.registerCustomEditor(String.class, "nombre", new NombreMayusculaEditor());
		binder.registerCustomEditor(String.class, "apellido", new NombreMayusculaEditor());
		binder.registerCustomEditor(Pais.class, "pais", paisEditor);
		binder.registerCustomEditor(Role.class, "roles", roleEditor);
	}

	@ModelAttribute("genero")
	public List<String> genero() {

		return Arrays.asList("Hombre", "Mujer");

	}

	@ModelAttribute("listaPaises")
	public List<Pais> listaPaises() {

		return paisService.listar();
	}

	@ModelAttribute("paises")
	public List<String> paises() {

		return Arrays.asList("Ecuador", "Brasil", "Peru", "Bolivia", "Mexico", "Noruega", "Canada");
	}

	@ModelAttribute("paisesMap")
	public Map<String, String> paisesMap() {

		Map<String, String> paises = new HashMap<>();
		paises.put("EC", "Ecuador");
		paises.put("BR", "Brasil");
		paises.put("PE", "Peru");
		paises.put("BO", "Bolivia");
		paises.put("MX", "Mexico");
		paises.put("NO", "Noruega");
		paises.put("CA", "Canada");

		return paises;

	}

	@ModelAttribute("listaRoles")
	public List<Role> listaRoles() {

		return this.roleService.listar();

	}

	@ModelAttribute("listaRolesString")
	public List<String> listaRolesString() {

		List<String> roles = new ArrayList<>();

		roles.add("ROLE_ADMIN");
		roles.add("ROLE_USER");
		roles.add("ROLE_MODERATOR");

		return roles;

	}

	@ModelAttribute("listaRolesMap")
	public Map<String, String> listaRolesMap() {

		Map<String, String> roles = new HashMap<>();
		roles.put("ROLE_ADMIN", "ADMINISTRADOR");
		roles.put("ROLE_USER", "USUARIO");
		roles.put("ROLE_MODERATOR", "MODERADOR");

		return roles;

	}

	@GetMapping("/form")
	public String form(Model model) {

		Usuario usuario = new Usuario();

		model.addAttribute("titulo", "Form");

		usuario.setNombre("Jonathan");
		usuario.setApellido("Navas");

		usuario.setIdentificador("123.456.789-K");

		usuario.setHabilitar(true);

		usuario.setValorSecreto("saludos hacker");

		usuario.setPais(new Pais(1, "EC", "Ecuador"));

		usuario.setRoles(Arrays.asList(new Role(1, "Administrador", "Role_admin")));

		model.addAttribute("usuario", usuario);

		return "form";
	}

	@PostMapping("/form")
	public String procesar(@Valid Usuario usuario, BindingResult result, Model model) {

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Form results");
			return "form";
		}

		return "redirect:/ver";
	}

	@GetMapping("/ver")
	public String ver(@SessionAttribute(name="usuario",required = false) Usuario usuario, Model model, SessionStatus status) {

		if(usuario==null) {
			return "redirect:/form";
		}
		model.addAttribute("titulo", "Form results");
		status.setComplete();
		return "resultado";
	}

}
