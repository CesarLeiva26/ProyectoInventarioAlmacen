package cibertec.edu.pe.ProyectoInventarioAlmacen.model.bd;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "detallerecepcion")
@Data
public class DetalleRecepcion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "iddetallere")
	private Integer iddetallere;

	@Column(name = "idrecepcion")
	private Integer idrecepcion;

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
