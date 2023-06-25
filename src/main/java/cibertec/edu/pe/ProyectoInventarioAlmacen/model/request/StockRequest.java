package cibertec.edu.pe.ProyectoInventarioAlmacen.model.request;

import java.util.Date;

import lombok.Data;

@Data
public class StockRequest {
	private Integer idStock;
	private Integer idProducto;
	private Integer idUbicacion;
	private Integer idLote;
	private Integer cantidad;
	private Integer idEstado;
	private String producto;
	private String descripcion;
	private String ubicacion;
	private String lote;
	private Date fechaFab;
	private Date fechaVen;
	private String nomEstado;
}
