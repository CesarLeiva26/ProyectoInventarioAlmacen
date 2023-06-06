package cibertec.edu.pe.ProyectoInventarioAlmacen.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cibertec.edu.pe.ProyectoInventarioAlmacen.model.bd.Pallet;
import cibertec.edu.pe.ProyectoInventarioAlmacen.service.PalletService;

@Controller
@RequestMapping("/pallet")
public class PalletController {

	@Autowired
	private PalletService palletService;
	
	@GetMapping("/listarPallets")
	@ResponseBody
	public List<Pallet> listarPallets(){
		return palletService.listarPallets();
	}
}