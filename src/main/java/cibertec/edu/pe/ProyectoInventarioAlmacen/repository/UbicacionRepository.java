package cibertec.edu.pe.ProyectoInventarioAlmacen.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cibertec.edu.pe.ProyectoInventarioAlmacen.model.bd.Ubicacion;

@Repository
public interface UbicacionRepository extends JpaRepository<Ubicacion, Integer> {
    List<Ubicacion> findUbicacionByUbicacionContainingIgnoreCase(String ubicacion);
}