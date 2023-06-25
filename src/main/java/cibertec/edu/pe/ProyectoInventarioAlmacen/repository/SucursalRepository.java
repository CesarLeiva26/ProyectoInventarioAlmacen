package cibertec.edu.pe.ProyectoInventarioAlmacen.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cibertec.edu.pe.ProyectoInventarioAlmacen.model.bd.Sucursal;

@Repository
public interface SucursalRepository extends JpaRepository<Sucursal, Integer>{

	List<Sucursal> findByEntidadIdentidad(Integer identidad);
}