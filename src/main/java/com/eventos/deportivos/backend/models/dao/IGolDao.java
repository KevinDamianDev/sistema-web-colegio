package com.eventos.deportivos.backend.models.dao;

import com.eventos.deportivos.backend.models.entity.Gol;
import org.springframework.data.repository.CrudRepository;

public interface IGolDao extends CrudRepository<Gol, Long> {
}
