package cibertec.edu.pe.ProyectoInventarioAlmacen.service;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import cibertec.edu.pe.ProyectoInventarioAlmacen.model.bd.Rolauth;
import cibertec.edu.pe.ProyectoInventarioAlmacen.model.bd.Usuarioauth;
import cibertec.edu.pe.ProyectoInventarioAlmacen.repository.RrolauthlRepository;
import cibertec.edu.pe.ProyectoInventarioAlmacen.repository.UsuarioRepositoryAuth;

@Service
public class UsuarioauthService {
	@Autowired
	private UsuarioRepositoryAuth usuarioRepositoryAuth;

	@Autowired
	private RrolauthlRepository rrolauthlRepository;

	private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

	public Usuarioauth buscarUsuarioPorNomusuario(String nomusuario) {
		
		return usuarioRepositoryAuth.findByNomusuario(nomusuario);
	}

	public Usuarioauth guardarUsuario(Usuarioauth usuarioauth) {
		usuarioauth.setPassword(bCryptPasswordEncoder.encode(usuarioauth.getPassword()));
		usuarioauth.setActivo(true);
		Rolauth rolauth = rrolauthlRepository.findByNomrol("ADMIN");
		usuarioauth.setRoles(new HashSet<Rolauth>(Arrays.asList(rolauth)));
		return usuarioRepositoryAuth.save(usuarioauth);
	}

}
