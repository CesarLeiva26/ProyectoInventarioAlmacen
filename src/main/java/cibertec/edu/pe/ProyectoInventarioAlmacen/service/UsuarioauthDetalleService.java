package cibertec.edu.pe.ProyectoInventarioAlmacen.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cibertec.edu.pe.ProyectoInventarioAlmacen.model.bd.Rolauth;
import cibertec.edu.pe.ProyectoInventarioAlmacen.model.bd.Usuarioauth;


@Service
public class UsuarioauthDetalleService implements UserDetailsService {

	@Autowired
	private UsuarioauthService usuarioauthService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuarioauth usuario = usuarioauthService.buscarUsuarioPorNomusuario(username);
		return usuarioPorAutenticacion(usuario, obtenerAutorizacionUsuario(usuario.getRoles()));
	}

	private List<GrantedAuthority> obtenerAutorizacionUsuario(Set<Rolauth> listRoles) {
		Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
		for (Rolauth rolauth : listRoles) {
			roles.add(new SimpleGrantedAuthority(rolauth.getNomrol()));
		}
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
		return grantedAuthorities;

	}

	private UserDetails usuarioPorAutenticacion(Usuarioauth usuario, List<GrantedAuthority> authorityList) {
		return new User(usuario.getNomusuario(), usuario.getPassword(), usuario.getActivo(), true, true, true,
				authorityList);
	}

}
