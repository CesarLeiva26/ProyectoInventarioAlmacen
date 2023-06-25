package cibertec.edu.pe.ProyectoInventarioAlmacen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cibertec.edu.pe.ProyectoInventarioAlmacen.model.bd.Rolauth;


@Repository
public interface RrolauthlRepository 
	extends JpaRepository<Rolauth, Integer>{
	
	Rolauth findByNomrol(String rolname);
}
