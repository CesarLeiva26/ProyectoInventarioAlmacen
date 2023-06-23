package cibertec.edu.pe.ProyectoInventarioAlmacen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cibertec.edu.pe.ProyectoInventarioAlmacen.model.bd.DetalleRecepcion;
import cibertec.edu.pe.ProyectoInventarioAlmacen.model.bd.Recepcion;
import cibertec.edu.pe.ProyectoInventarioAlmacen.repository.DetalleRecepcionRepository;
import cibertec.edu.pe.ProyectoInventarioAlmacen.repository.RecepcionRepository;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.print.attribute.standard.DateTimeAtCompleted;
import javax.persistence.PersistenceContext;

@Service
@Transactional
public class RecepcionService {
	@Autowired
	private RecepcionRepository recepcionRepository;

	@Autowired
	private DetalleRecepcionRepository detalleRecepcionRepository;

	public Recepcion saveRecepcion(Recepcion recepcion) {
		System.out.println("Tipo de movimiento: " + recepcion.getDetallesRecepcion());
		Recepcion savedRecepcion = recepcionRepository.save(recepcion);
		
		List<DetalleRecepcion> detallesRecepcion = recepcion.getDetallesRecepcion();
		for (DetalleRecepcion detalle : detallesRecepcion) {
			detalle.setRecepcion(savedRecepcion);
			detalleRecepcionRepository.save(detalle);

			// Actualizar el stock
			Integer idProducto = detalle.getIdProducto();
			Integer cantidad = detalle.getCantidad();
			// Llamar al método para actualizar el stock
		//	actualizarStock(idProducto, cantidad);
		}

		return savedRecepcion;
	}

	private void actualizarStock(Integer idProducto, Integer cantidad) {
		// Lógica para actualizar el stock en la tabla "stock"
		// ...
	}
	/*
	 * public Recepcion guardarRecepcion(Recepcion recepcion) {
	 * System.out.println("Tipo de movimiento: " + recepcion.getTipoMov());
	 * System.out.println("Fecha de movimiento: " + recepcion.getFechaMov());
	 * System.out.println("ID de entidad: " + recepcion.getEntidad());
	 * System.out.println("ID de sucursal: " + recepcion.getSucursal());
	 * System.out.println("Documento de respuesta: " + recepcion.getDocResp());
	 * System.out.println("Número de documento de respuesta: " +
	 * recepcion.getNumDocResp()); System.out.println("Notas: " +
	 * recepcion.getNotas()); System.out.println("Detalles de recepcion: " +
	 * recepcion.getDetallesRecepcion());
	 * 
	 * Recepcion savedRecepcion = recepcionRepository.save(recepcion); int
	 * idRecepcion = savedRecepcion.getIdRecepcion(); StoredProcedureQuery query =
	 * entityManager
	 * .createStoredProcedureQuery("SP_GUARDAR_RECEPCION_DETALLE_ACTUALIZAR_STOCK");
	 * 
	 * query.registerStoredProcedureParameter("p_tipoMov", String.class,
	 * ParameterMode.IN); query.registerStoredProcedureParameter("p_fechaMov",
	 * Date.class, ParameterMode.IN);
	 * query.registerStoredProcedureParameter("p_idEntidad", Integer.class,
	 * ParameterMode.IN); query.registerStoredProcedureParameter("p_idSucursal",
	 * Integer.class, ParameterMode.IN);
	 * query.registerStoredProcedureParameter("p_docResp", String.class,
	 * ParameterMode.IN); query.registerStoredProcedureParameter("p_numDocResp",
	 * String.class, ParameterMode.IN);
	 * query.registerStoredProcedureParameter("p_notas", String.class,
	 * ParameterMode.IN); query.registerStoredProcedureParameter("p_detalles",
	 * String.class, ParameterMode.IN);
	 * 
	 * // Set the parameter values query.setParameter("p_tipoMov",
	 * recepcion.getTipoMov()); query.setParameter("p_fechaMov",
	 * recepcion.getFechaMov()); query.setParameter("p_idEntidad",
	 * recepcion.getIdRecepcion()); query.setParameter("p_idSucursal",
	 * recepcion.getSucursal()); query.setParameter("p_docResp",
	 * recepcion.getDocResp()); query.setParameter("p_numDocResp",
	 * recepcion.getNumDocResp()); query.setParameter("p_notas",
	 * recepcion.getNotas()); query.setParameter("p_detalles", ""); // Provide the
	 * JSON details here
	 * 
	 * // Execute the stored procedure query.execute();
	 * query.registerStoredProcedureParameter("p_detalles", String.class,
	 * ParameterMode.IN);
	 * 
	 * return savedRecepcion; }
	 */

}