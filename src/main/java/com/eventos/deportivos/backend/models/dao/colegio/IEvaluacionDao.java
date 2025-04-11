package com.eventos.deportivos.backend.models.dao.colegio;

import com.eventos.deportivos.backend.models.entity.colegio.Evaluacion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IEvaluacionDao extends CrudRepository<Evaluacion, Long> {

    @Query("SELECT DISTINCT e FROM CursoEvaluacion ce " +
            "JOIN ce.evaluacion e " +
            "JOIN ce.curso c " +
            "JOIN GradoCurso gc ON gc.curso.idCurso = c.idCurso " +
            "JOIN gc.grado g " +
            "JOIN DocenteGrado dg ON dg.grado.idGrado = g.idGrado " +
            "JOIN dg.docente d " +
            "JOIN d.persona p " +
            "JOIN AlumnoGrado ag ON ag.grado.idGrado = g.idGrado " +
            "WHERE p.idPersona = :idPersona " +
            "AND g.idGrado = :idGrado " +
            "AND ag.periodo.idPeriodo = :idPeriodo " +
            "AND c.idCurso = :idCurso")
    List<Evaluacion> obtenerEvaluacionesPorDocenteYCurso(
            @Param("idPersona") Long idPersona,
            @Param("idGrado") Long idGrado,
            @Param("idPeriodo") Long idPeriodo,
            @Param("idCurso") Long idCurso
    );

}
