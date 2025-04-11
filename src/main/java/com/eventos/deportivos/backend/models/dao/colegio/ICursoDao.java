package com.eventos.deportivos.backend.models.dao.colegio;

import com.eventos.deportivos.backend.models.entity.colegio.Curso;
import com.eventos.deportivos.backend.models.entity.colegio.Grado;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICursoDao extends CrudRepository<Curso, Long> {

    @Query("SELECT DISTINCT dc.curso FROM DocenteCurso dc " +
            "INNER JOIN dc.docente d " +
            "INNER JOIN d.persona p " +
            "INNER JOIN dc.grado g " +
            "WHERE p.idPersona = :idPersona " +
            "AND g.idGrado = :idGrado " +
            "AND dc.periodo.idPeriodo = :idPeriodo")
    List<Curso> findCursosByDocenteGradoPeriodo(
            @Param("idPersona") Long idPersona,
            @Param("idGrado") Long idGrado,
            @Param("idPeriodo") Long idPeriodo);
}