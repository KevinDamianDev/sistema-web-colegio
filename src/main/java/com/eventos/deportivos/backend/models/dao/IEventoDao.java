package com.eventos.deportivos.backend.models.dao;

import com.eventos.deportivos.backend.models.entity.Evento;
import org.springframework.data.repository.CrudRepository;

public interface IEventoDao extends CrudRepository<Evento, Long> {
}
