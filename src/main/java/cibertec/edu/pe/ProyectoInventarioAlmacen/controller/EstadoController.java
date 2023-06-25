package cibertec.edu.pe.ProyectoInventarioAlmacen.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cibertec.edu.pe.ProyectoInventarioAlmacen.model.bd.Estado;
import cibertec.edu.pe.ProyectoInventarioAlmacen.service.EstadoService;

@Controller
@RequestMapping("/estado")
public class EstadoController {

	@Autowired
	private EstadoService estadoService;

	@GetMapping("/frmestado")
	public String frmMantEstado() {
		// model.addAttribute("listaentidades", entidadService.listarEntidad());
		return "estado/frmestado";
	}

	@GetMapping("/listarEstados")
	@ResponseBody
	public List<Estado> listarEstados() {
		return estadoService.listarEstados();
	}

}
