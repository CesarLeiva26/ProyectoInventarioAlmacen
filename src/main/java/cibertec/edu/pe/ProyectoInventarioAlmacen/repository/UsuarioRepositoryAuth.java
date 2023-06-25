package cibertec.edu.pe.ProyectoInventarioAlmacen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cibertec.edu.pe.ProyectoInventarioAlmacen.model.bd.Usuarioauth;


@Repository
public interface UsuarioRepositoryAuth extends
 JpaRepository<Usuarioauth, Integer>{
	
	Usuarioauth findByEmail(String email);
	
	Usuarioauth findByNomusuario(String username);

}
