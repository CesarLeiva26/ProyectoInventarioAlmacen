package cibertec.edu.pe.ProyectoInventarioAlmacen.repository;




import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import cibertec.edu.pe.ProyectoInventarioAlmacen.model.bd.Entidad;

@Repository
public interface EntidadRepository extends JpaRepository<Entidad, Integer> {

}