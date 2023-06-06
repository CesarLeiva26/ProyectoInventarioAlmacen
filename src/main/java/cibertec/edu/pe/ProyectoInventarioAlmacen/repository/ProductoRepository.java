package cibertec.edu.pe.ProyectoInventarioAlmacen.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cibertec.edu.pe.ProyectoInventarioAlmacen.model.bd.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {

}
