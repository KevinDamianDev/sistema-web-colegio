package com.eventos.deportivos.backend.models.dao.colegio;

import com.eventos.deportivos.backend.models.entity.colegio.Periodo;
import org.springframework.data.repository.CrudRepository;

public interface IPeriodoDao extends CrudRepository<Periodo, Long> {
}
