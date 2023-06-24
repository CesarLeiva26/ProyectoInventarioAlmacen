package cibertec.edu.pe.ProyectoInventarioAlmacen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cibertec.edu.pe.ProyectoInventarioAlmacen.model.request.RecepcionRequest;
import cibertec.edu.pe.ProyectoInventarioAlmacen.model.response.RecepcionResponse;
import cibertec.edu.pe.ProyectoInventarioAlmacen.service.RecepcionService;

@Controller
@RequestMapping("/recepcion")
public class RecepcionController {

	@Autowired
	private RecepcionService recepcionService;

	@GetMapping("/orden_recepcion")
	public String ordenRecepcion() {
		return "recepcion/orden_recepcion";
	}

	@GetMapping("/frmrecepcion")
	public String nuevaRecepcion() {
		return "recepcion/frmrecepcion";
	}

	@PostMapping("/guardarRecepcion")
	@ResponseBody
	public RecepcionResponse guardarRecepcion(@RequestBody RecepcionRequest recepcionRequest) {
		String mensaje = "Recepción guardada correctamente";
		boolean respuesta = true;
		try {
			recepcionService.guardarRecepcion(recepcionRequest);
		} catch (Exception e) {
			mensaje = "Error al guardar la recepción. Se realizó rollback.";
			respuesta = false;
		}
		return RecepcionResponse.builder().mensaje(mensaje).respuesta(respuesta).build();
	}

}
