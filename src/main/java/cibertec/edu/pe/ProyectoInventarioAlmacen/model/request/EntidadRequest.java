
package cibertec.edu.pe.ProyectoInventarioAlmacen.model.request;

import lombok.Data;

@Data
public class EntidadRequest {

	private Integer identidad;
	private String entidad;
	private String nombreentidad;
	private String tipo;
	private String direccion;
	private String correo;
	private String telefono;
	private String notas;
}

