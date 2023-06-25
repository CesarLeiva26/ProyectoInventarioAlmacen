package cibertec.edu.pe.ProyectoInventarioAlmacen.model.request;

import java.time.LocalDate;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import cibertec.edu.pe.ProyectoInventarioAlmacen.model.bd.DetalleRecepcion;
import lombok.Data;

@Data
public class RecepcionRequest {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idrecepcion")
    private Integer idrecepcion;

    @Column(name = "tipomov")
    private String tipomov;

    @Column(name = "fechamov")
    private LocalDate fechamov;

    @Column(name = "identidad")
    private Integer identidad;

    @Column(name = "idsucursal")
    private Integer idsucursal;

    @Column(name = "docresp")
    private String docresp;

    @Column(name = "numdocresp")
    private String numdocresp;

    @Column(name = "notas")
    private String notas;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "idrecepcion")
    private List<DetalleRecepcion> detallesrecepcion;

}
