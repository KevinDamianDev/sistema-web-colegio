package com.eventos.deportivos.backend.models.dao;

import com.eventos.deportivos.backend.models.entity.EquipoJugador;
import org.springframework.data.repository.CrudRepository;

public interface IEquipoJugadorDao extends CrudRepository<EquipoJugador, Long> {
}
