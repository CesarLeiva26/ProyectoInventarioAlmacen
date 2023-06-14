package cibertec.edu.pe.ProyectoInventarioAlmacen.controller;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ubicacion")
public class UbicacionController {

	@GetMapping("/frmubicacion")
	public String frmMantUbicacion() {
		// model.addAttribute("listaentidades", entidadService.listarEntidad());
		return "ubicacion/frmubicacion";
	}
	
}
