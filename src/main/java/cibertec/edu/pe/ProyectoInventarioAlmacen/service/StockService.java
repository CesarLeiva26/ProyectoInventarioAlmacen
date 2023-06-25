package cibertec.edu.pe.ProyectoInventarioAlmacen.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cibertec.edu.pe.ProyectoInventarioAlmacen.model.bd.Stock;
import cibertec.edu.pe.ProyectoInventarioAlmacen.repository.StockRepository;

@Service
public class StockService {
	  @Autowired
	    private StockRepository stockRepository;

	    public List<Stock> listarStock() {
	        return stockRepository.findAll();
	    }
}