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
@Table(name="pallet")
public class Pallet {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idpallet;
	
	@Column(name="descripcion")
	private String descripcion;
}
