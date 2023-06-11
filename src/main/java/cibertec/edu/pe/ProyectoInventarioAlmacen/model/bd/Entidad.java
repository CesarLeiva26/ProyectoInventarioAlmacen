package cibertec.edu.pe.ProyectoInventarioAlmacen.model.bd;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "entidad")
public class Entidad {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "identidad")
	private Integer identidad;
	
	@Column(name = "entidad")
	private String entidad;
	
	@Column(name = "nombreentidad")
	private String nombreEntidad;
	
	@Column(name = "tipo")
	private String tipo;
	
	@Column(name = "direccion")
	private String direccion;
	
	@Column(name = "correo")
	private String correo;
	
	@Column(name = "telefono")
	private String telefono;
	
	@Column(name = "notas")
	private String notas;
	
}
