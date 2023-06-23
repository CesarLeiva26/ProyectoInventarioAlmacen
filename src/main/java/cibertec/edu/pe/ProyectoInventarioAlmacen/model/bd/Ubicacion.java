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
	private int idUbicacion;

	@Column(name = "ubicacion")
	private String ubicacion;

	@Column(nullable = false)
	private Date fechaCreacion;
}