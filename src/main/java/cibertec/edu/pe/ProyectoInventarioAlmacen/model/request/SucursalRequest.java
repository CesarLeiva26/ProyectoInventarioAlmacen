package cibertec.edu.pe.ProyectoInventarioAlmacen.model.request;

import lombok.Data;

@Data
public class SucursalRequest {
	
	private Integer idsucursal;
	private String nomsucursal;
	private Integer identidad;
	private String notas;

}