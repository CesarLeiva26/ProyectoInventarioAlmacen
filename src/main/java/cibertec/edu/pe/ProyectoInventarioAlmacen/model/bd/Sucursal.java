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
@Table(name="sucursal")
public class Sucursal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idsucursal;
	
	@Column(name="nomsucursal")
	private String nomsucursal;
	
	@ManyToOne
	@JoinColumn(name="identidad")
	private Entidad entidad;
	
	@Column(name="notas")
	private String notas;
	
}
