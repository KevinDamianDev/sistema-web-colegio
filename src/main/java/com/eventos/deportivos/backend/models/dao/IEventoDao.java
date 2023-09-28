package com.eventos.deportivos.backend.models.dao;

import com.eventos.deportivos.backend.models.entity.Equipo;
import com.eventos.deportivos.backend.models.entity.Evento;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IEventoDao extends CrudRepository<Evento, Long> {

    @Query("SELECT pr.equipo FROM EventoEquipo pr WHERE pr.evento.idEvento = :id")
    List<Equipo> findEquipoByEventoId(@Param("id") Long id);
}
