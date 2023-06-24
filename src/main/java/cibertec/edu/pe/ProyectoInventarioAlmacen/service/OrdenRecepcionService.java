package cibertec.edu.pe.ProyectoInventarioAlmacen.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cibertec.edu.pe.ProyectoInventarioAlmacen.model.bd.OrdenRecepcion;
import cibertec.edu.pe.ProyectoInventarioAlmacen.repository.OrdenRecepcionRepository;

@Service
public class OrdenRecepcionService {

	@Autowired
	private OrdenRecepcionRepository ordenRecepcionRepository;

	public List<OrdenRecepcion> listarOrdenRecepcion() {
		return ordenRecepcionRepository.findAll();
	}

}
