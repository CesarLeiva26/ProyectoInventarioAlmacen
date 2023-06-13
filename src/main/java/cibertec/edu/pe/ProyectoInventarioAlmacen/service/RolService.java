package cibertec.edu.pe.ProyectoInventarioAlmacen.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cibertec.edu.pe.ProyectoInventarioAlmacen.model.bd.Rol;
import cibertec.edu.pe.ProyectoInventarioAlmacen.repository.Rollrepository;

@Service
public class RolService {

	@Autowired
	private	Rollrepository repo;
	
	public List<Rol> listaRoles(){
		
		return repo.findAll();
	}
	
}
