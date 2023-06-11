package cibertec.edu.pe.ProyectoInventarioAlmacen.model.response;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResultadoentidadResponse {
	private Boolean respuesta;
	private String mensaje;
}


