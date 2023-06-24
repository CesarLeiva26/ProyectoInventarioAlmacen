package cibertec.edu.pe.ProyectoInventarioAlmacen.model.bd;

import java.util.Date;

import lombok.Data;

@Data
public class StockResponse {
    private String producto;
    private String descripcion;
    private Integer cantidad;
    private String ubicacion;
    private String lote;
    private Date fechaFab;
    private Date fechaVen;
    private String nomEstado;
}
