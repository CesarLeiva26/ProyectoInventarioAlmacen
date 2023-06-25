package cibertec.edu.pe.ProyectoInventarioAlmacen.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cibertec.edu.pe.ProyectoInventarioAlmacen.model.bd.Unidad;
import cibertec.edu.pe.ProyectoInventarioAlmacen.service.UnidadService;

@Controller
@RequestMapping("/unidad")
public class UnidadController {

	@Autowired
	private UnidadService unidadService;
	
	@GetMapping("/listarUnidades")
	@ResponseBody
	public List<Unidad> listarunidades(){
		return unidadService.listarUnidades();
	}
}