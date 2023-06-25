package cibertec.edu.pe.ProyectoInventarioAlmacen.repository;

 
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cibertec.edu.pe.ProyectoInventarioAlmacen.model.bd.Producto;
@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    List<Producto> findByProductoContainingIgnoreCase(String termino);

}