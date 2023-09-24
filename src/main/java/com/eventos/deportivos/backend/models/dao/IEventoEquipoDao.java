package com.eventos.deportivos.backend.models.dao;

import com.eventos.deportivos.backend.models.entity.EventoEquipo;
import org.springframework.data.repository.CrudRepository;

public interface IEventoEquipoDao extends CrudRepository<EventoEquipo, Long> {
}
