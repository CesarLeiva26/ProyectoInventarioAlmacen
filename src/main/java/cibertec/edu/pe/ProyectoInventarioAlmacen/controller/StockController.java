package cibertec.edu.pe.ProyectoInventarioAlmacen.controller; 
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import cibertec.edu.pe.ProyectoInventarioAlmacen.model.bd.Stock;
import cibertec.edu.pe.ProyectoInventarioAlmacen.service.StockService;



@Controller
@RequestMapping("/stock")
public class StockController {
	
    @Autowired
    private StockService stockService;

    @GetMapping("/frmstockxubi")
    public String frmStockxubi(Model model) {
        model.addAttribute("listastock", stockService.listarStock());
        return "stock/frmstockxubi";
    }
    
    @GetMapping("/listarStock")
    @ResponseBody
    public List<Stock> listarStock() {
        return stockService.listarStock();

    }
    
}



