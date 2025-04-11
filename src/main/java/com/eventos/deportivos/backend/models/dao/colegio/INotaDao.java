package com.eventos.deportivos.backend.models.dao.colegio;

import com.eventos.deportivos.backend.models.entity.colegio.Nota;
import org.springframework.data.repository.CrudRepository;

public interface INotaDao extends CrudRepository<Nota, Long> {
}
