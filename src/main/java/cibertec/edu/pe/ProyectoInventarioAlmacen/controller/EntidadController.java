package cibertec.edu.pe.ProyectoInventarioAlmacen.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;

import cibertec.edu.pe.ProyectoInventarioAlmacen.model.bd.Entidad;

import cibertec.edu.pe.ProyectoInventarioAlmacen.model.request.EntidadRequest;
import cibertec.edu.pe.ProyectoInventarioAlmacen.model.response.ResultadoResponse;


import cibertec.edu.pe.ProyectoInventarioAlmacen.service.EntidadService;

@Controller
@RequestMapping("/entidad")
public class EntidadController {
	
	@Autowired
	private EntidadService entidadService;
	
	@GetMapping("/frmentidad")
	public String frmMantEntidad(Model model) {
		model.addAttribute("listaentidades", entidadService.listarEntidad());
		return "entidad/frmentidad";
	}
	
	@PostMapping("/registrarEntidad")
	@ResponseBody
	public ResultadoResponse registrarEntidad(@RequestBody EntidadRequest entidadRequest) {
	    String mensaje = "Registro de Entidad Exitoso";
	    boolean respuesta = true;
	    try {
	        Entidad entidad = new Entidad();
	        if (entidadRequest.getIdentidad() != null && entidadRequest.getIdentidad() > 0) {
	            entidad.setIdentidad(entidadRequest.getIdentidad());
	        }
	        entidad.setEntidad(entidadRequest.getEntidad());
	        entidad.setNombreEntidad(entidadRequest.getNombreentidad());
	        entidad.setTipo(entidadRequest.getTipo());
	        entidad.setDireccion(entidadRequest.getDireccion());
	        entidad.setCorreo(entidadRequest.getCorreo());
	        entidad.setTelefono(entidadRequest.getTelefono());
	        entidad.setNotas(entidadRequest.getNotas());
	        entidadService.registrarEntidad(entidad);
	    } catch (Exception e) {
	        mensaje = "Registro de Entidad sin Éxito";
	        respuesta = false;
	    }
	    return ResultadoResponse.builder().mensaje(mensaje).respuesta(respuesta).build();
	}

	@DeleteMapping("/eliminarEntidad")
	@ResponseBody
	public ResultadoResponse eliminarEntidad(@RequestBody EntidadRequest entidadRequest) {
		String mensaje = "Eliminación de Entidad Exitoso";
		Boolean respuesta = true;
		try {
			entidadService.eliminarEntidad(entidadRequest.getIdentidad());
		} catch (Exception e) {
			mensaje = "Eliminación de Entidad sin Éxito";
			respuesta = false;
		}
		return ResultadoResponse.builder().mensaje(mensaje).respuesta(respuesta).build();
	}
	

	@GetMapping("/listarEntidades")
	@ResponseBody
	public List<Entidad> listarEntidades() {
		return entidadService.listarEntidad();
	}


	
	
}