package com.eventos.deportivos.backend.models.dao;

import com.eventos.deportivos.backend.models.entity.EquipoEstadistica;
import org.springframework.data.repository.CrudRepository;

public interface IEquipoEstadisticaDao extends CrudRepository<EquipoEstadistica, Long> {
}
