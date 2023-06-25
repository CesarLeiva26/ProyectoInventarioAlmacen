package cibertec.edu.pe.ProyectoInventarioAlmacen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cibertec.edu.pe.ProyectoInventarioAlmacen.model.bd.Lote;
import cibertec.edu.pe.ProyectoInventarioAlmacen.model.bd.Producto;
import cibertec.edu.pe.ProyectoInventarioAlmacen.model.bd.Stock;
import cibertec.edu.pe.ProyectoInventarioAlmacen.model.bd.Ubicacion;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {}