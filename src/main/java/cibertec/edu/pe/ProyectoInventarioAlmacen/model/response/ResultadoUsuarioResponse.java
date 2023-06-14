package cibertec.edu.pe.ProyectoInventarioAlmacen.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResultadoUsuarioResponse {
public String mensaje;
boolean respuesta;
}
