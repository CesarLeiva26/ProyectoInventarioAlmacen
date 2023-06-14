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

import cibertec.edu.pe.ProyectoInventarioAlmacen.model.bd.Rol;
import cibertec.edu.pe.ProyectoInventarioAlmacen.model.bd.Usuario;
import cibertec.edu.pe.ProyectoInventarioAlmacen.model.request.UsuarioRequest;
import cibertec.edu.pe.ProyectoInventarioAlmacen.model.response.ResultadoUsuarioResponse;
import cibertec.edu.pe.ProyectoInventarioAlmacen.service.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioContoller {

	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("/usuario")
	public String listaprincipalUsuario(Model m) {
		m.addAttribute("listausuario", usuarioService.listausuario());
		return "usuario/usuario";
	}
	
	@ResponseBody
	@PostMapping("/guardarusuario")
	public ResultadoUsuarioResponse usuario(@RequestBody UsuarioRequest req) {
		String mensaje = "¡Se registro con extito!";
		boolean respuesta = true;
		
		try {
			Rol r=Rol.builder()
					.idrol(req.getIdrol()).build();
			
			Usuario u=Usuario.builder()
					.idusuario(req.getIdusuario())
					.nombre(req.getNombre())
					.apellidos(req.getApellidos())
					.usuario(req.getUsuario())
					.correo(req.getCorreo())
					.telefono(req.getTelefono())
					.rol(r).build();
			
			usuarioService.guardarusuario(u);
		} catch (Exception e) {
		mensaje="Error en reistrar " +e.getMessage();
		respuesta=false;
		}
		
		return ResultadoUsuarioResponse.builder().mensaje(mensaje).respuesta(respuesta).build();

	}
	
	@ResponseBody
	@GetMapping("/listausuarioconajax")
	public List<Usuario> listausuarioconajax(){
		
		return usuarioService.listausuario(); 
		
	}
	@ResponseBody
	@DeleteMapping("/eliminarusuario")
	public ResultadoUsuarioResponse eliminarusuario(@RequestBody UsuarioRequest req) {
		String mensaje="¡Usuario eliminado con exito!";
		boolean respuesta =true;
		try {
			usuarioService.eliminarusuario(req.getIdusuario());
		} catch (Exception e) {
			mensaje="Error en eliminar el usuario "+ req.getIdusuario()+" "+e.getMessage();
			respuesta=false;
		}
		
		return ResultadoUsuarioResponse.builder().mensaje(mensaje).respuesta(respuesta).build();
	}
	
	
	
	
}
