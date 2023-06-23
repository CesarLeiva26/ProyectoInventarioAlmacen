package cibertec.edu.pe.ProyectoInventarioAlmacen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import cibertec.edu.pe.ProyectoInventarioAlmacen.model.bd.Recepcion;
import cibertec.edu.pe.ProyectoInventarioAlmacen.service.RecepcionService;

@Controller
@RequestMapping("/recepcion")
public class RecepcionController {

	@Autowired
	private RecepcionService recepcionService;

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
	
	@PostMapping("/recepcion")
    public ResponseEntity<Recepcion> createRecepcion(@RequestBody Recepcion recepcion) {
        Recepcion savedRecepcion = recepcionService.saveRecepcion(recepcion);
        return ResponseEntity.ok(savedRecepcion);
    }
}
