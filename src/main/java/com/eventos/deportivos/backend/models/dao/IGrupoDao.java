package com.eventos.deportivos.backend.models.dao;

import com.eventos.deportivos.backend.models.entity.Grupo;
import org.springframework.data.repository.CrudRepository;

public interface IGrupoDao extends CrudRepository<Grupo, Long> {
}
