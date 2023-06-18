package cibertec.edu.pe.ProyectoInventarioAlmacen.model.sp;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class SpInsertarlote {
	@Id
    private Integer idlote;
    private String lote;
    private String fechaFab;

	
}
