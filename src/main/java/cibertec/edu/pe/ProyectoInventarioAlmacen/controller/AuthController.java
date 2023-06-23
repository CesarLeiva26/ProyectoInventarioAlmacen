package cibertec.edu.pe.ProyectoInventarioAlmacen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import cibertec.edu.pe.ProyectoInventarioAlmacen.model.bd.Usuario;
import cibertec.edu.pe.ProyectoInventarioAlmacen.service.RolService;
import cibertec.edu.pe.ProyectoInventarioAlmacen.service.UsuarioService;

@Controller
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired 
	private RolService rolService;

	
	
	@GetMapping("/login")
	public String login() {

		return "auth/frmlogin";
	}
//alterno para emviar daots al selec por implementar
	@GetMapping("/registrar")
	public String registrar(Model m) {
		
		m.addAttribute("listausuario",rolService.listaRoles());
		return "auth/frmregistrar";
	}

	
	@PostMapping("/guardarUsuario")
	public String guardausuarioauth(@ModelAttribute Usuario u, Model m) {
		usuarioService.guardarusuarioauth(u);
		m.addAttribute("registrocorrecto", true);
		return "auth/frmregistrar";

	}
}
