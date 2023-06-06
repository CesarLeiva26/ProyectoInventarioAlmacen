package cibertec.edu.pe.ProyectoInventarioAlmacen.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResultadoproductoResponse {

	private Boolean respuesta;
	private String mensaje;
}