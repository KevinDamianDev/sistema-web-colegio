package com.eventos.deportivos.backend.models.dao;

import com.eventos.deportivos.backend.models.entity.EventoEquipo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface IEventoEquipoDao extends CrudRepository<EventoEquipo, Long> {

    @Query("SELECT pr FROM EventoEquipo pr WHERE pr.evento.idEvento = :idEvento AND pr.equipo.idEquipo = :idEquipo")
    EventoEquipo buscarPorIdEventoYIdEquipo(@Param("idEvento") Long idEvento, @Param("idEquipo") Long idEquipo);
}
