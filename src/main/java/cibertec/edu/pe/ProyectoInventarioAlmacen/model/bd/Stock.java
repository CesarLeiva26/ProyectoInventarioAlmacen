package cibertec.edu.pe.ProyectoInventarioAlmacen.model.bd;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "stock")
public class Stock {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idstock")
	private Integer idstock;

	@ManyToOne
	@JoinColumn(name ="idproducto")
	private Producto producto;

	@Column(name = "cantidad")
	private int cantidad;
	 
	@ManyToOne
	@JoinColumn(name ="idubicacion")
	private Ubicacion ubicacion;	    

	@ManyToOne
	@JoinColumn(name ="idlote")
	private Lote lote;	    
	    
	@ManyToOne
	@JoinColumn(name ="idestado")
	    private Estado estado;
}
