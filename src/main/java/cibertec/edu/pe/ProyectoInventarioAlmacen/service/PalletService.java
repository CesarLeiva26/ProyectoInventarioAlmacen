package cibertec.edu.pe.ProyectoInventarioAlmacen.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cibertec.edu.pe.ProyectoInventarioAlmacen.model.bd.Pallet;
import cibertec.edu.pe.ProyectoInventarioAlmacen.repository.PalletRepository;
  
@Service
public class PalletService {

	@Autowired
	private PalletRepository  palletrepository;

	public List<Pallet> listarPallets() {
		return palletrepository.findAll();
	}
}