package com.eventos.deportivos.backend.models.dao;

import com.eventos.deportivos.backend.models.entity.Equipo;
import com.eventos.deportivos.backend.models.entity.Jugador;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IEquipoDao extends CrudRepository<Equipo, Long> {

    @Query("SELECT pr.jugador FROM EquipoJugador pr WHERE pr.equipo.idEquipo = :id")
    List<Jugador> findJugadorByEquipoId(@Param("id") Long id);
}
