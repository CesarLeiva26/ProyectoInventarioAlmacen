package cibertec.edu.pe.ProyectoInventarioAlmacen.model.bd;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "producto")
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idproducto;

	@Column(name = "producto")
	private String producto;

	@Column(name = "descripcion")
	private String descripcion;

	@ManyToOne
	@JoinColumn(name = "idunidad")
	private Unidad unidad;

	@Column(name = "envase")
	private String envase;

	@Column(name = "peso")
	private String peso;
}