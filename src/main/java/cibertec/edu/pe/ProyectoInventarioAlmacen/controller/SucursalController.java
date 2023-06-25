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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cibertec.edu.pe.ProyectoInventarioAlmacen.model.bd.Entidad;
import cibertec.edu.pe.ProyectoInventarioAlmacen.model.bd.Sucursal;
import cibertec.edu.pe.ProyectoInventarioAlmacen.model.request.SucursalRequest;
import cibertec.edu.pe.ProyectoInventarioAlmacen.model.response.ResultadosucursalResponse;
import cibertec.edu.pe.ProyectoInventarioAlmacen.service.SucursalService;

@Controller
@RequestMapping("/sucursal")
public class SucursalController {

	@Autowired
	private SucursalService sucursalService;

	@GetMapping("/frmsucursal")
	public String frmMantSucursal(Model model) {
		model.addAttribute("listasucursales", sucursalService.listarSucursal());
		return "sucursal/frmsucursal";
	}

	@PostMapping("/registrarSucursal")
	@ResponseBody
	public ResultadosucursalResponse registrarSucursal(@RequestBody SucursalRequest sucursalRequest) {
		String mensaje = "Registro de Sucursal Exitoso";
		boolean respuesta = true;
		try {
			Sucursal objsucursal = new Sucursal();
			if (sucursalRequest.getIdsucursal() != null && sucursalRequest.getIdsucursal() > 0) {
				objsucursal.setIdsucursal(sucursalRequest.getIdsucursal());
			}
			objsucursal.setNomsucursal(sucursalRequest.getNomsucursal());
			objsucursal.setNotas(sucursalRequest.getNotas());
			Entidad objEntidad = new Entidad();
			objEntidad.setIdentidad(sucursalRequest.getIdentidad());
			objsucursal.setEntidad(objEntidad);
			sucursalService.registrarSucursal(objsucursal);

		} catch (Exception e) {
			mensaje = "Registro Sucursal sin Exito";
			respuesta = false;
		}
		return ResultadosucursalResponse.builder().mensaje(mensaje).respuesta(respuesta).build();
	}

	@DeleteMapping("/eliminarSucursal")
	@ResponseBody
	public ResultadosucursalResponse eliminarSucursal(@RequestBody SucursalRequest sucursalRequest) {
		String mensaje = "Eliminar Sucursal Exitoso";
		Boolean respuesta = true;
		try {
			sucursalService.eliminarSucursal(sucursalRequest.getIdsucursal());
		} catch (Exception e) {
			mensaje = "Eliminacion de Sucursal sin Exito";
			respuesta = false;
		}
		return ResultadosucursalResponse.builder().mensaje(mensaje).respuesta(respuesta).build();
	}

	@GetMapping("/listarSucursales")
	@ResponseBody
	public List<Sucursal> listarSucursales() {
		return sucursalService.listarSucursal();
	}

	@GetMapping("/listarSucursalesPorEntidad")
	@ResponseBody
	public List<Sucursal> listarSucursalesPorEntidad(@RequestParam("identidad") Integer identidad) {
		System.out.print(identidad);
		return sucursalService.listarSucursalesPorEntidad(identidad);
	}

}