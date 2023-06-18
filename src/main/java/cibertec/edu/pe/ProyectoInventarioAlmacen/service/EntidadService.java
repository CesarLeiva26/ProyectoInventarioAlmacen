package cibertec.edu.pe.ProyectoInventarioAlmacen.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cibertec.edu.pe.ProyectoInventarioAlmacen.model.bd.Entidad;

import cibertec.edu.pe.ProyectoInventarioAlmacen.repository.EntidadRepository;

@Service
public class EntidadService {
    @Autowired
    private EntidadRepository entidadRepository;

    public List<Entidad> listarEntidad() {
        return entidadRepository.findAll();
    }

    public void registrarEntidad(Entidad entidad) {
        entidadRepository.save(entidad);
    }

    public void eliminarEntidad(Integer identidad) {
        entidadRepository.deleteById(identidad);
    }

}
