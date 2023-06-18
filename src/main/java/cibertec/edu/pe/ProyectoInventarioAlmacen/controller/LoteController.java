package cibertec.edu.pe.ProyectoInventarioAlmacen.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cibertec.edu.pe.ProyectoInventarioAlmacen.model.bd.Lote;
import cibertec.edu.pe.ProyectoInventarioAlmacen.model.request.LoteRequest;
import cibertec.edu.pe.ProyectoInventarioAlmacen.model.response.ResultadoResponse;
import cibertec.edu.pe.ProyectoInventarioAlmacen.service.LoteService;

@Controller
@RequestMapping("/lote")
public class LoteController {
    private static final Logger logger = LoggerFactory.getLogger(LoteController.class);

    @Autowired
    private LoteService loteService;

    @GetMapping("/frmlote")
    public String frmMantLote(Model model) {
        model.addAttribute("listalotes", loteService.listarLotes());
        return "lote/frmlote";
    }

    @PostMapping("/registrarLote")
    @ResponseBody
    public ResultadoResponse registrarLote(@RequestBody Lote lote) {
        logger.info("Registrando lote: " + lote);
        logger.info("Valor de fechafab: " + lote.getFechafab());
        String mensaje = "Lote registrado correctamente";
        boolean respuesta = true;
        try {
            loteService.registrarLote(lote);
        } catch (Exception ex) {
            logger.error("Error al registrar el lote", ex);
            mensaje = "Lote no registrado";
            respuesta = false;
        }
        return ResultadoResponse.builder()
                .mensaje(mensaje)
                .respuesta(respuesta)
                .build();
    }

    @DeleteMapping("/eliminarLote")
	@ResponseBody
	public ResultadoResponse eliminarLote(@RequestBody LoteRequest loteRequest) {
		String mensaje = "Eliminación de Lote Exitoso";
		Boolean respuesta = true;
		try {
			loteService.eliminarLote(loteRequest.getIdlote());
		} catch (Exception e) {
			mensaje = "Eliminación de Lote sin Éxito";
			respuesta = false;
		}
		return ResultadoResponse.builder().mensaje(mensaje).respuesta(respuesta).build();
	}
    
    @GetMapping("/listarLotes")
    @ResponseBody
    public List<Lote> listarLotes() {
        return loteService.listarLotes();
    }
}
