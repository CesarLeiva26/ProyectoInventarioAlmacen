package cibertec.edu.pe.ProyectoInventarioAlmacen.model.request;

import lombok.Data;
import java.util.Date;

@Data
public class LoteRequest {

    private Integer idlote;
    private String lote;
    private Date fechaFab;
    private Date fechaVen;
}
