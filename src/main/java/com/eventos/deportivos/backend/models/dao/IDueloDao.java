package com.eventos.deportivos.backend.models.dao;

import com.eventos.deportivos.backend.models.entity.Duelo;
import org.springframework.data.repository.CrudRepository;

public interface IDueloDao extends CrudRepository<Duelo, Long> {
    // FASES QUE PERTENECEN A UN EVENTO
  /*  @Query("SELECT e FROM Fase e WHERE e.evento.idEvento = :eventoId")
    List<EquipoGrupoDuelo> findEquiposByDuelo(@Param("eventoId") Long eventoId);*/
}
