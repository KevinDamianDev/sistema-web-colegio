package com.eventos.deportivos.backend.models.dao;

import com.eventos.deportivos.backend.models.entity.PersonaEvento;
import org.springframework.data.repository.CrudRepository;

public interface IPersonaEventoDao extends CrudRepository<PersonaEvento, Long> {
}
