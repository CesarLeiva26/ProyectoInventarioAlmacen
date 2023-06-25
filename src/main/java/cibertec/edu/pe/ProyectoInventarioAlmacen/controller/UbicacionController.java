package cibertec.edu.pe.ProyectoInventarioAlmacen.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cibertec.edu.pe.ProyectoInventarioAlmacen.model.bd.Ubicacion;
import cibertec.edu.pe.ProyectoInventarioAlmacen.service.UbicacionService;

@Controller
@RequestMapping("/ubicacion")
public class UbicacionController {

	@Autowired
	private UbicacionService ubicacionService;

	@GetMapping("/frmubicacion")
	public String frmMantUbicacion() {
		// model.addAttribute("listaentidades", entidadService.listarEntidad());
		return "ubicacion/frmubicacion";
	}

	@GetMapping("/buscarUbicaciones")
	@ResponseBody
	public List<Ubicacion> buscarUbicaciones(@RequestParam("termino") String termino) {
	    return ubicacionService.buscarUbicacionesPorTermino(termino);
	}
}
