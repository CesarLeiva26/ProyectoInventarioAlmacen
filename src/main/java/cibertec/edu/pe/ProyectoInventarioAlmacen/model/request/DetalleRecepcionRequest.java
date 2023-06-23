package cibertec.edu.pe.ProyectoInventarioAlmacen.model.request;

import java.util.List;

import javax.persistence.Column;

import cibertec.edu.pe.ProyectoInventarioAlmacen.model.bd.DetalleRecepcion;

public class DetalleRecepcionRequest {
	private int idProducto;
    private int idUbicacion;
    private int idLote;
    private int idEstado;
    private int cantidad;
}
