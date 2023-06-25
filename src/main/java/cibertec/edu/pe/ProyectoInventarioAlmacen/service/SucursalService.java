package cibertec.edu.pe.ProyectoInventarioAlmacen.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cibertec.edu.pe.ProyectoInventarioAlmacen.model.bd.Sucursal;
import cibertec.edu.pe.ProyectoInventarioAlmacen.repository.SucursalRepository;

@Service
public class SucursalService {

	@Autowired
	private SucursalRepository sucursalRepository;

	public List<Sucursal> listarSucursal() {
		return sucursalRepository.findAll();
	}

	public void registrarSucursal(Sucursal sucursal) {
		sucursalRepository.save(sucursal);
	}

	public void eliminarSucursal(Integer idsucursal) {
		sucursalRepository.deleteById(idsucursal);
	}

	public List<Sucursal> listarSucursalesPorEntidad(Integer identidad) {
		return sucursalRepository.findByEntidadIdentidad(identidad);
	}

}