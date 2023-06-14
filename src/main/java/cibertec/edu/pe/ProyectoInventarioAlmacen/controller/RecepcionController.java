package cibertec.edu.pe.ProyectoInventarioAlmacen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/recepcion")
public class RecepcionController {
	
	@GetMapping("/orden_recepcion")
	public String ordenRecepcion() {
		// model.addAttribute("listaentidades", entidadService.listarEntidad());
		return "recepcion/orden_recepcion";
	}
	
	
	@GetMapping("/frmrecepcion")
    public String nuevaRecepcion() {
        // Agrega atributos al modelo y realiza otras operaciones necesarias
        return "recepcion/frmrecepcion";
    }
}
