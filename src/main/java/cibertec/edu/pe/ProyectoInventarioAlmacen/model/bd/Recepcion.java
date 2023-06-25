package cibertec.edu.pe.ProyectoInventarioAlmacen.model.bd;

import lombok.Data;

import javax.persistence.*;

import cibertec.edu.pe.ProyectoInventarioAlmacen.model.request.DetalleRecepcionRequest;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "recepcion")
@Data
public class Recepcion {

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
    @JoinColumn(name = "idrepecion")
    private List<DetalleRecepcion> detallesrecepcion;

}