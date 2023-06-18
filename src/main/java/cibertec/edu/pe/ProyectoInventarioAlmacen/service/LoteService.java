package cibertec.edu.pe.ProyectoInventarioAlmacen.service;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cibertec.edu.pe.ProyectoInventarioAlmacen.model.bd.Lote;
import cibertec.edu.pe.ProyectoInventarioAlmacen.repository.LoteRepository;

@Service
public class LoteService {

	@Autowired
    private LoteRepository loteRepository;

	@Transactional
	public void registrarLote(Lote lote) {
	    loteRepository.insertarLote(lote.getLote(), lote.getFechafab());
	}

    public List<Lote> listarLotes() {
        return loteRepository.findAll();
    }
    
    public void eliminarLote(Integer idlote) {
    	loteRepository.deleteById(idlote);
    }
}
