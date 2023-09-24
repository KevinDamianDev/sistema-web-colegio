package com.eventos.deportivos.backend.models.dao;

import com.eventos.deportivos.backend.models.entity.Persona;
import org.springframework.data.repository.CrudRepository;

public interface IPersonaDao extends CrudRepository<Persona, Long> {
}
