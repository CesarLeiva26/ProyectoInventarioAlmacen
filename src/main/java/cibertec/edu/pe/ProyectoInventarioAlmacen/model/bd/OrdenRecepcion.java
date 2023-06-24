package cibertec.edu.pe.ProyectoInventarioAlmacen.model.bd;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "recepcion")
@Data
public class OrdenRecepcion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idrecepcion")
	private Integer idrecepcion;

	@Column(name = "tipomov")
	private String tipomov;

	@Column(name = "fechamov")
	private LocalDate fechamov;

	@ManyToOne
	@JoinColumn(name = "identidad")
	private Entidad identidad;

	@Column(name = "idsucursal")
	private Integer idsucursal;

	@Column(name = "docresp")
	private String docresp;

	@Column(name = "numdocresp")
	private String numdocresp;

	@Column(name = "notas")
	private String notas;
}
