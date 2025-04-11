package com.eventos.deportivos.backend.models.dao.colegio;

import com.eventos.deportivos.backend.models.entity.colegio.Horario;
import org.springframework.data.repository.CrudRepository;

public interface IHorarioDao extends CrudRepository<Horario, Long> {
}
