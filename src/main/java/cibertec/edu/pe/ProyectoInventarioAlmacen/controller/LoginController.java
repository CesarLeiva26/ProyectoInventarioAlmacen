package cibertec.edu.pe.ProyectoInventarioAlmacen.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import cibertec.edu.pe.ProyectoInventarioAlmacen.model.bd.Usuarioauth;
import cibertec.edu.pe.ProyectoInventarioAlmacen.service.UsuarioauthService;
@RequestMapping("/auth")
@Controller
public class LoginController {
	
	@Autowired
	private UsuarioauthService usuarioauthService;
	
	@GetMapping("/login")
	public String login() {
		
		return "auth/login";
	}
	@GetMapping("/registrar")
	public String registrar( Model model) {
		model.addAttribute("registroCorrecto", false);
		
		return "auth/registrar";
	}

	@PostMapping("/guardarUsuario")
	public String guardarUsuario(@Valid @ModelAttribute Usuarioauth usuario, BindingResult bindingResult, Model model) {
		usuarioauthService.guardarUsuario(usuario);
		model.addAttribute("registroCorrecto", true);
		return "auth/registrar";
	}
	
}
