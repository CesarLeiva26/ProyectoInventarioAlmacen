package cibertec.edu.pe.ProyectoInventarioAlmacen.service;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cibertec.edu.pe.ProyectoInventarioAlmacen.model.bd.DetalleRecepcion;
import cibertec.edu.pe.ProyectoInventarioAlmacen.model.bd.Recepcion;
import cibertec.edu.pe.ProyectoInventarioAlmacen.model.request.RecepcionRequest;
import cibertec.edu.pe.ProyectoInventarioAlmacen.repository.RecepcionRepository;

@Service
@Transactional
public class RecepcionService {

	@Autowired
	private EntityManager entityManager;

	public void guardarRecepcion(RecepcionRequest recepcionRequest) {

		Recepcion recepcion = new Recepcion();
		recepcion.setIdrecepcion(recepcionRequest.getIdrecepcion());
		recepcion.setTipomov(recepcionRequest.getTipomov());
		recepcion.setFechamov(recepcionRequest.getFechamov());
		recepcion.setIdentidad(recepcionRequest.getIdentidad());
		recepcion.setIdsucursal(recepcionRequest.getIdsucursal());
		recepcion.setDocresp(recepcionRequest.getDocresp());
		recepcion.setNumdocresp(recepcionRequest.getNumdocresp());
		recepcion.setNotas(recepcionRequest.getNotas());
		recepcion.setDetallesrecepcion(recepcionRequest.getDetallesrecepcion());

		StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("ST_GUARDAR_RECEPCION");
		storedProcedure.registerStoredProcedureParameter("tipomov", String.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter("fechamov", Date.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter("identidad", Integer.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter("idsucursal", Integer.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter("docresp", String.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter("numdocresp", String.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter("notas", String.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter("detallesrecepcion", String.class, ParameterMode.IN);

		storedProcedure.setParameter("tipomov", recepcion.getTipomov());
		storedProcedure.setParameter("fechamov", java.sql.Date.valueOf(recepcion.getFechamov()));
		storedProcedure.setParameter("identidad", recepcion.getIdentidad());
		storedProcedure.setParameter("idsucursal", recepcion.getIdsucursal());
		storedProcedure.setParameter("docresp", recepcion.getDocresp());
		storedProcedure.setParameter("numdocresp", recepcion.getNumdocresp());
		storedProcedure.setParameter("notas", recepcion.getNotas());
		storedProcedure.setParameter("detallesrecepcion", convertToJson(recepcion.getDetallesrecepcion()));

		storedProcedure.execute();
	}

	private Object convertToJson(List<DetalleRecepcion> detallesrecepcion) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			String json = objectMapper.writeValueAsString(detallesrecepcion);
			System.out.println(json);
			return json;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}

}