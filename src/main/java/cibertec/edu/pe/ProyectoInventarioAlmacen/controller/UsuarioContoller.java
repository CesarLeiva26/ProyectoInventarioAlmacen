package cibertec.edu.pe.ProyectoInventarioAlmacen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class UsuarioContoller {
	@GetMapping("/usuario")
	public String usuario() {
		return "usuario";
	}
}
