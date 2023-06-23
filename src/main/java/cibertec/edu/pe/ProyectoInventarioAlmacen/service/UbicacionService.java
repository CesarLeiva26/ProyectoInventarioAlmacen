package cibertec.edu.pe.ProyectoInventarioAlmacen.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cibertec.edu.pe.ProyectoInventarioAlmacen.model.bd.Ubicacion;
import cibertec.edu.pe.ProyectoInventarioAlmacen.repository.UbicacionRepository;

@Service
public class UbicacionService {

    @Autowired
    private UbicacionRepository ubicacionRepository;

    public List<Ubicacion> buscarUbicacionesPorTermino(String termino) {
        return ubicacionRepository.findByUbicacionContainingIgnoreCase(termino);
    }
    
}