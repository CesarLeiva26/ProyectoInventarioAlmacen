package cibertec.edu.pe.ProyectoInventarioAlmacen.model.bd;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ubicacion")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ubicacion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idubicacion")
	private Integer idubicacion;

	private String ubicacion;

	@Column(name = "fechacreacion")
	private Date fechacreacion;
}