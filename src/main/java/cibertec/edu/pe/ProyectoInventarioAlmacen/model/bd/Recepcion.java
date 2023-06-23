package cibertec.edu.pe.ProyectoInventarioAlmacen.model.bd;

import lombok.Data;

import javax.persistence.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "recepcion")
@Data
public class Recepcion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idRecepcion;

	private String tipoMov;
	private LocalDate fechaMov;
	private Integer idEntidad;
	private Integer idSucursal;
	private String docResp;
	private String numDocResp;
	private String notas;

	@OneToMany(mappedBy = "recepcion", cascade = CascadeType.ALL)
	private List<DetalleRecepcion> detallesRecepcion;
}