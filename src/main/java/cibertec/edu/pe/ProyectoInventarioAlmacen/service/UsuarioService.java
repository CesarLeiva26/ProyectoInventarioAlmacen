package cibertec.edu.pe.ProyectoInventarioAlmacen.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cibertec.edu.pe.ProyectoInventarioAlmacen.model.bd.Usuario;
import cibertec.edu.pe.ProyectoInventarioAlmacen.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repo;
	
	public List<Usuario> listausuario(){
		return repo.findAll();
	}
	
	public void guardarusuario(Usuario u) {
		
		repo.save(u);
	}
	
	public void eliminarusuario(Integer idusuario) {
		 
		repo.deleteById(idusuario);
	}
	
	public Usuario buscarporID(Integer idusuario) {
		return repo.findById(idusuario).orElse(null);
	}

}
