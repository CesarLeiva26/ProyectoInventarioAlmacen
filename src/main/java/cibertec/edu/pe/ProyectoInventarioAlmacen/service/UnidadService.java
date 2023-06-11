package cibertec.edu.pe.ProyectoInventarioAlmacen.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cibertec.edu.pe.ProyectoInventarioAlmacen.model.bd.Unidad;
import cibertec.edu.pe.ProyectoInventarioAlmacen.repository.UnidadRepository;
  
@Service
public class UnidadService {

	@Autowired
	private UnidadRepository unidadrepository;

	public List<Unidad> listarUnidades() {
		return unidadrepository.findAll();
	}
}