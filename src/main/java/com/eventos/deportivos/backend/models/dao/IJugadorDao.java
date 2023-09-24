package com.eventos.deportivos.backend.models.dao;

import com.eventos.deportivos.backend.models.entity.Jugador;
import org.springframework.data.repository.CrudRepository;

public interface IJugadorDao extends CrudRepository<Jugador, Long> {
}
