package com.eventos.deportivos.backend.models.dao;

import com.eventos.deportivos.backend.models.entity.Duelo;
import com.eventos.deportivos.backend.models.entity.Fecha;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IFechaDao extends CrudRepository<Fecha, Long> {

    @Query("SELECT e FROM Duelo e WHERE e.fecha.idFecha = :fechaId")
    List<Duelo> findDueloByFecha(@Param("fechaId") Long fechaId);
}
