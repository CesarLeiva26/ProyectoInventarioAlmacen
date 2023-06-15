package cibertec.edu.pe.ProyectoInventarioAlmacen.controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


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
		m.addAttribute("idusuario", 0);
		m.addAttribute("listausuario", usuarioService.listausuario());
		m.addAttribute("cajaalerta", false);
		return "usuario/usuario";
	}

	@PostMapping("/usuario")
	public String buscarporID(@RequestParam("idusuario") Integer idusuario, Model m) {
		String mensaje;
		Usuario usuarioporid = usuarioService.buscarporID(idusuario);
		if (usuarioporid != null) {
			m.addAttribute("usuarioporid", usuarioporid);
			return "redirect:/usuario/usuario/" + idusuario;
		} else {
			mensaje = "No se encontró ese usuario";
			m.addAttribute("listausuario", usuarioService.listausuario());
			m.addAttribute("cajaalerta", true);
			m.addAttribute("mensaje", mensaje);

			return "/usuario/usuario";
		}

	}

	@GetMapping("/usuario/{idusuario}")
	public String mostrarUsuario(@PathVariable("idusuario") Integer idusuario, Model m) {
		String mensaje;
		Usuario usuarioporid = usuarioService.buscarporID(idusuario);
		if (usuarioporid != null) {
			m.addAttribute("usuarioporid", usuarioporid);
			m.addAttribute("listausuario", usuarioService.listausuario());
			mensaje = "¡Busqueda exitosa!";
		} else {
			mensaje = "¡El codigo insertdao no existe!";

		}
		m.addAttribute("mensaje", mensaje);
		return "usuario/usuario";
	}

	@ResponseBody
	@PostMapping("/guardarusuario")
	public ResultadoUsuarioResponse usuario(@RequestBody UsuarioRequest req) {
		String mensaje = "¡Se registro con extito!";
		boolean respuesta = true;

		try {
			Rol r = Rol.builder().idrol(req.getIdrol()).build();

			Usuario u = Usuario.builder().idusuario(req.getIdusuario()).nombre(req.getNombre())
					.apellidos(req.getApellidos()).usuario(req.getUsuario()).correo(req.getCorreo())
					.telefono(req.getTelefono()).rol(r).build();

			usuarioService.guardarusuario(u);
		} catch (Exception e) {
			mensaje = "Error en reistrar " + e.getMessage();
			respuesta = false;
		}

		return ResultadoUsuarioResponse.builder().mensaje(mensaje).respuesta(respuesta).build();

	}

	@ResponseBody
	@GetMapping("/listausuarioconajax")
	public List<Usuario> listausuarioconajax() {

		return usuarioService.listausuario();

	}

	@ResponseBody
	@DeleteMapping("/eliminarusuario")
	public ResultadoUsuarioResponse eliminarusuario(@RequestBody UsuarioRequest req) {
		String mensaje = "¡Usuario eliminado con exito!";
		boolean respuesta = true;
		try {
			usuarioService.eliminarusuario(req.getIdusuario());
		} catch (Exception e) {
			mensaje = "Error en eliminar el usuario " + req.getIdusuario() + " " + e.getMessage();
			respuesta = false;
		}

		return ResultadoUsuarioResponse.builder().mensaje(mensaje).respuesta(respuesta).build();
	}

	@Autowired
	private ResourceLoader resourceLoader;
	@PostMapping("/guadarimagen")
	public String cargaimagen(@RequestParam("file") MultipartFile file,
	                          @RequestParam("idusuario") Integer idusuario,Model m) {
	    String mensaje;
	    if (!file.isEmpty()) {
	        try {
	           // String nombreOriginal = file.getOriginalFilename();
	            String nuevoNombre = idusuario + ".jpg";
	            String rutaImagen = "/img/usuariosPerfiles/" + nuevoNombre;
	            
	            String rutaAbsoluta = resourceLoader.getResource("classpath:static" + rutaImagen).getFile().getAbsolutePath();
	            File destino = new File(rutaAbsoluta);
	            
	            file.transferTo(destino);
	            
	            // Aquí puedes realizar cualquier otra lógica adicional si es necesario
	            
	            return "redirect:/usuario/usuario/" + idusuario;
	        } catch (Exception e) {
	            mensaje = "error en subir imagen " + e.getMessage();
	             m.addAttribute("cajaalerta",true);
	             e.printStackTrace();
	            m.addAttribute("mensaje", mensaje);
	        }
	    }
	    
	    return "redirect:/usuario/usuario";
	}



}
