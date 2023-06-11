package cibertec.edu.pe.ProyectoInventarioAlmacen.model.request;

import lombok.Data;

@Data
public class ProductoRequest {

	private Integer idproducto;
	private String descripcion;
	private Integer idunidad;
	private String envase;
	private String peso;

}
