package cibertec.edu.pe.ProyectoInventarioAlmacen.repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cibertec.edu.pe.ProyectoInventarioAlmacen.model.bd.Lote;

@Repository
public interface LoteRepository extends JpaRepository<Lote, Integer> {

    @Transactional
    @Modifying
    @Query(value = "{call SpInsertarlote(:lote, :fechaFab)}", nativeQuery = true)
    void insertarLote(@Param("lote") String lote, @Param("fechaFab") Date fechafab);

    List<Lote> findByLoteContainingIgnoreCase(String termino);

}

