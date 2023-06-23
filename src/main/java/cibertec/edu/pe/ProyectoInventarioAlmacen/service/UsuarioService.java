package cibertec.edu.pe.ProyectoInventarioAlmacen.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import cibertec.edu.pe.ProyectoInventarioAlmacen.model.bd.Rol;
import cibertec.edu.pe.ProyectoInventarioAlmacen.model.bd.Usuario;
import cibertec.edu.pe.ProyectoInventarioAlmacen.repository.Rollrepository;
import cibertec.edu.pe.ProyectoInventarioAlmacen.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repo;
	
	@Autowired
	private Rollrepository reporol;
	
	
	private BCryptPasswordEncoder bc =new BCryptPasswordEncoder();
	
	
	public List<Usuario> listausuario(){
		return repo.findAll();
	}
	
	public void guardarusuario(Usuario u) {
		
		repo.save(u);
	}
	
	public void eliminarusuario(Integer idusuario) {
		 
		repo.deleteById(idusuario);
	}
	
	//busca por id y se muesntra la foto guardada
	public Usuario buscarporID(Integer idusuario) {
		return repo.findById(idusuario).orElse(null);
	}

	
	public Usuario buscarusuarioporusuario (String usuario) {
		return repo.findByNomusuario(usuario);
	}
	
	
	
	
	public Usuario guardarusuarioauth(Usuario u) {
		u.setPassword(bc.encode(u.getPassword()));
		u.setActivo(true);
		
		
		Rol rol= reporol.findByNombrerol("digitador");
		u.setRoles(new HashSet<Rol>(Arrays.asList(rol)));
		
		return repo.save(u);
	}
	
	
}
