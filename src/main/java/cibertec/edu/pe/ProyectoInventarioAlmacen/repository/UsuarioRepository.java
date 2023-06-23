package cibertec.edu.pe.ProyectoInventarioAlmacen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cibertec.edu.pe.ProyectoInventarioAlmacen.model.bd.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
	
	public Usuario findByUsuario(String usuario);

	//este parametro es emviado al usuarioservicio
	Usuario findByNomusuario(String username);

}
