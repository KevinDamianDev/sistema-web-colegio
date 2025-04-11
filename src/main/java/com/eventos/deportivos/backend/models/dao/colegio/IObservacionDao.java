package com.eventos.deportivos.backend.models.dao.colegio;

import com.eventos.deportivos.backend.models.entity.colegio.Observacion;
import org.springframework.data.repository.CrudRepository;

public interface IObservacionDao extends CrudRepository<Observacion, Long> {
}
