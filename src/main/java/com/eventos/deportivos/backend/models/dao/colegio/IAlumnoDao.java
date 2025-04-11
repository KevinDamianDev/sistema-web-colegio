package com.eventos.deportivos.backend.models.dao.colegio;

import com.eventos.deportivos.backend.models.entity.colegio.Alumno;
import org.springframework.data.repository.CrudRepository;

public interface IAlumnoDao extends CrudRepository<Alumno, Long> {
}
