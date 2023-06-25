package cibertec.edu.pe.ProyectoInventarioAlmacen.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cibertec.edu.pe.ProyectoInventarioAlmacen.model.bd.Lote;
import cibertec.edu.pe.ProyectoInventarioAlmacen.model.bd.OrdenRecepcion;
import cibertec.edu.pe.ProyectoInventarioAlmacen.service.EntidadService;
import cibertec.edu.pe.ProyectoInventarioAlmacen.service.OrdenRecepcionService;

@Controller
@RequestMapping("/ordenrecepcion")
public class OrdenRecepcionController {
	
	@Autowired
	private OrdenRecepcionService ordenRecepcionService;
	
	  @GetMapping("/listarordenrecepcion")
	    @ResponseBody
	    public List<OrdenRecepcion> listarOrdenRecepcion() {
	        return ordenRecepcionService.listarOrdenRecepcion();
	    }
	    

}
