package com.eventos.deportivos.backend.models.dao;

import com.eventos.deportivos.backend.models.entity.Fase;
import org.springframework.data.repository.CrudRepository;

public interface IFaseDao extends CrudRepository<Fase, Long> {
}
