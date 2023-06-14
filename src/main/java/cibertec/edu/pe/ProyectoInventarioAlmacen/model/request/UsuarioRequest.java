package cibertec.edu.pe.ProyectoInventarioAlmacen.model.request;

import cibertec.edu.pe.ProyectoInventarioAlmacen.model.bd.Rol;
import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class UsuarioRequest {
	private Integer idusuario; 
	private Integer idrol;
	private String nombre;
	private String apellidos;
	private String	usuario; 
	private String correo;
	private String telefono;
}
