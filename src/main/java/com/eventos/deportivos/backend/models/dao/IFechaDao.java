package com.eventos.deportivos.backend.models.dao;

import com.eventos.deportivos.backend.models.entity.Fecha;
import org.springframework.data.repository.CrudRepository;

public interface IFechaDao extends CrudRepository<Fecha, Long> {
}
