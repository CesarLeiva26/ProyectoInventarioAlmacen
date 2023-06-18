package cibertec.edu.pe.ProyectoInventarioAlmacen.model.bd;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "lote")
public class Lote {
	 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idlote")
    private Integer idlote;
    
    @Column(name = "lote")
    private String lote;
    
    @Column(name = "fechafab")
    private Date fechafab;
    
    @Column(name = "fechaven")
    private Date fechaven;
}
