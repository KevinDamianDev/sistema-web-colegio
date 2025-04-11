package com.eventos.deportivos.backend.models.dao.colegio;

import com.eventos.deportivos.backend.models.entity.colegio.Docente;
import org.springframework.data.repository.CrudRepository;

public interface IDocenteDao extends CrudRepository<Docente, Long> {

}
