package cibertec.edu.pe.ProyectoInventarioAlmacen.model.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class RolRequest {
	private Integer idrol;
	private String nombrerol;
}
