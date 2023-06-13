package cibertec.edu.pe.ProyectoInventarioAlmacen.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cibertec.edu.pe.ProyectoInventarioAlmacen.model.bd.Rol;
import cibertec.edu.pe.ProyectoInventarioAlmacen.service.RolService;

@Controller
@RequestMapping("/rol")
public class RollController {

	@Autowired
	private RolService rolService;

	@GetMapping("listarol")
	@ResponseBody
	public List<Rol> listarol() {
		return rolService.listaRoles();
	}

}
