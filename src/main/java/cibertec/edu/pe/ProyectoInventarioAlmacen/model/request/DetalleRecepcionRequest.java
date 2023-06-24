package cibertec.edu.pe.ProyectoInventarioAlmacen.model.request;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import cibertec.edu.pe.ProyectoInventarioAlmacen.model.bd.DetalleRecepcion;

public class DetalleRecepcionRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "iddetallere")
	private Integer iddetallere;

	@Column(name = "idrepecion")
	private Integer idrepecion;

	@Column(name = "idproducto")
	private Integer idproducto;

	@Column(name = "idubicacion")
	private Integer idubicacion;

	@Column(name = "idlote")
	private Integer idlote;

	@Column(name = "idestado")
	private Integer idestado;

	@Column(name = "cantidad")
	private Integer cantidad;
    
}
