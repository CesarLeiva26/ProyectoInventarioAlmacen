package cibertec.edu.pe.ProyectoInventarioAlmacen.model.bd;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "detallerecepcion")
@Data
public class DetalleRecepcion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idDetalleRe;

	@ManyToOne
	@JoinColumn(name = "idRecepcion")
	private Recepcion recepcion;
	private Integer idProducto;
	private Integer idUbicacion;
	private Integer idLote;
	private Integer idEstado;
	private Integer cantidad;

}
