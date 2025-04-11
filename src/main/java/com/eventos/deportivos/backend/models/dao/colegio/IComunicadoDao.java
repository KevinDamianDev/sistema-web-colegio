package com.eventos.deportivos.backend.models.dao.colegio;

import com.eventos.deportivos.backend.models.entity.colegio.Comunicado;
import org.springframework.data.repository.CrudRepository;

public interface IComunicadoDao extends CrudRepository<Comunicado, Long> {
}
