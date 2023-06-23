package cibertec.edu.pe.ProyectoInventarioAlmacen.model.bd;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "stock")
@Data
public class Stock {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idStock;
	private Integer idProducto;
	private Integer idUbicacion;
	private Integer idLote;
	private Integer cantidad;
	private Integer idEstado;
}