package cibertec.edu.pe.ProyectoInventarioAlmacen.model.bd;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "stock")
@Data
public class Stock {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idstock")
	private Integer idstock;
	
	@Column(name = "idproducto")
	private Integer idproducto;
	
	@Column(name = "idubicacion")
	private Integer idubicacion;
	
	@Column(name = "idlote")
	private Integer idlote;
	
	@Column(name = "cantidad")
	private Integer cantidad;
	
	@Column(name = "idestado")
	private Integer idestado;
}