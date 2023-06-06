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

import cibertec.edu.pe.ProyectoInventarioAlmacen.model.bd.Pallet;
import cibertec.edu.pe.ProyectoInventarioAlmacen.model.bd.Producto;
import cibertec.edu.pe.ProyectoInventarioAlmacen.model.request.ProductoRequest;
import cibertec.edu.pe.ProyectoInventarioAlmacen.model.response.ResultadoproductoResponse;
import cibertec.edu.pe.ProyectoInventarioAlmacen.service.ProductoService;

@Controller
@RequestMapping("/producto")
public class ProductoController {
	
	@Autowired
	private ProductoService productoService;	
	@GetMapping("/frmproducto")
	public String frmMantProducto(Model model) {
		model.addAttribute("listaproductos", productoService.listarProducto());
		return "producto/frmproducto";
	}
	
	@PostMapping("/registrarProducto")
	@ResponseBody
	public ResultadoproductoResponse registrarProducto(@RequestBody ProductoRequest productoRequest) {
		String mensaje = "Registro de Producto Exitoso";
		Boolean respuesta = true;
		try {
			Producto objproducto = new Producto();
			if(productoRequest.getIdproducto() > 0) {
				objproducto.setIdproducto(productoRequest.getIdproducto());
			}
			objproducto.setNombre(productoRequest.getNombre());
			objproducto.setDescripcion(productoRequest.getDescripcion());
			Pallet objPallet = new Pallet();
			objPallet.setIdpallet(productoRequest.getIdpallet());
			objproducto.setPallet(objPallet);
			productoService.registrarProducto(objproducto);
		} catch (Exception e) {
			mensaje = "Registro de Producto sin Éxito";
			respuesta = false;
		}
		return ResultadoproductoResponse.builder().mensaje(mensaje).respuesta(respuesta).build();
	}
	
	@DeleteMapping("/eliminarProducto")
	@ResponseBody
	public ResultadoproductoResponse eliminarProducto(@RequestBody ProductoRequest productoRequest) {
		String mensaje = "Eliminación de Producto Exitoso";
		Boolean respuesta = true;
		try {
			productoService.eliminarProducto(productoRequest.getIdproducto());
		} catch (Exception e) {
			mensaje = "Eliminación de Producto sin Éxito";
			respuesta = false;
		}
		return ResultadoproductoResponse.builder().mensaje(mensaje).respuesta(respuesta).build();
	}
	
	@GetMapping("/listarProductos")
	@ResponseBody
	public List<Producto> listarProductos(){
		return productoService.listarProducto();
	}	
}