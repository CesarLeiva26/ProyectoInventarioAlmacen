package cibertec.edu.pe.ProyectoInventarioAlmacen.service;

import java.util.ArrayList;
import java.util.Collection;
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

import cibertec.edu.pe.ProyectoInventarioAlmacen.model.bd.Rol;
import cibertec.edu.pe.ProyectoInventarioAlmacen.model.bd.Usuario;
import java.util.Collections;

@Service
public class UsuarioDetalleServicio implements UserDetailsService {

	@Autowired
	private UsuarioService service;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario us=service.buscarusuarioporusuario(username);
		if (us != null) {
			return usuarioPorAutenticacion(us, obtenerautorizaciondeusuario(us.getRoles()));
		} else {
			return usuarioPorAutenticacionDefault(); // Devuelve un UserDetails sin roles
		}
		
		
	}
	
	private List<GrantedAuthority> obtenerautorizaciondeusuario(Set<Rol> listRoles) {
		Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
		for (Rol rol : listRoles) {
			roles.add(new SimpleGrantedAuthority(rol.getNombrerol()));
		}
				List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
		return grantedAuthorities;

	}
	
	
	private UserDetails usuarioPorAutenticacion(Usuario us, List<GrantedAuthority> authorityList) {
		return new User(us.getUsuario(), us.getPassword(), us.getActivo(), true, true, true,
				authorityList);
	}
	
	private UserDetails usuarioPorAutenticacionDefault() {
		return new User("1234", "1234",  false, true, true, true, Collections.emptyList());
	}
	
}

/*@Service
public class UsuarioDetalleServicio implements UserDetailsService {

	@Autowired
	private UsuarioService service;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario us = service.buscarusuarioporusuario(username);

		if (us == null) {
			throw new UsernameNotFoundException("Usuario no encontrado: " + username);
		}

		return new User(us.getUsuario(), us.getPassword(), us.getActivo(), true, true, true, new ArrayList<>());
	}

}
*/

