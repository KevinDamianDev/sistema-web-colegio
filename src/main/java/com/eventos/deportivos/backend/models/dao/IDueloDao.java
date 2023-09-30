package com.eventos.deportivos.backend.models.dao;

import com.eventos.deportivos.backend.models.entity.Duelo;
import com.eventos.deportivos.backend.models.entity.EquipoGrupoDuelo;
import com.eventos.deportivos.backend.models.entity.Fase;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IDueloDao extends CrudRepository<Duelo, Long> {
    // FASES QUE PERTENECEN A UN EVENTO
  /*  @Query("SELECT e FROM Fase e WHERE e.evento.idEvento = :eventoId")
    List<EquipoGrupoDuelo> findEquiposByDuelo(@Param("eventoId") Long eventoId);*/
}
