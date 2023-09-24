package com.eventos.deportivos.backend.models.dao;

import com.eventos.deportivos.backend.models.entity.Equipo;
import org.springframework.data.repository.CrudRepository;

public interface IEquipoDao extends CrudRepository<Equipo, Long> {
}
