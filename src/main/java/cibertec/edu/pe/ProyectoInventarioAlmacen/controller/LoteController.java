package cibertec.edu.pe.ProyectoInventarioAlmacen.controller;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/lote")
public class LoteController {

	@GetMapping("/frmlote")
	public String frmMantUbicacion() {
		// model.addAttribute("listaentidades", entidadService.listarEntidad());
		return "lote/frmlote";
	}
	
}
