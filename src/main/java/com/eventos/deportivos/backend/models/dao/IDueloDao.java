package com.eventos.deportivos.backend.models.dao;

import com.eventos.deportivos.backend.models.entity.Duelo;
import org.springframework.data.repository.CrudRepository;

public interface IDueloDao extends CrudRepository<Duelo, Long> {
}
