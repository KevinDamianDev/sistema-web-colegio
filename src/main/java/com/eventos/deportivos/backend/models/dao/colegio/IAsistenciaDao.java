package com.eventos.deportivos.backend.models.dao.colegio;

import com.eventos.deportivos.backend.models.entity.colegio.Asistencia;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IAsistenciaDao extends CrudRepository<Asistencia, Long> {

    @Query("SELECT a FROM Asistencia a " +
            "JOIN a.alumnoCurso ac " +
            "JOIN AlumnoGrado ag ON ac.alumno.idAlumno = ag.alumno.idAlumno " +
            "JOIN Grado g ON ag.grado.idGrado = g.idGrado " +
            "JOIN Curso c ON ac.curso.idCurso = c.idCurso " +
            "JOIN DocenteCurso dc ON a.docenteCurso.idDocenteCurso = dc.idDocenteCurso " +
            "JOIN DocenteGrado dg ON dc.docente.idDocente = dg.docente.idDocente AND dg.grado.idGrado = g.idGrado " +
            "WHERE g.idGrado = :idGrado " +
            "AND c.idCurso = :idCurso " +
            "AND ag.periodo.idPeriodo = :idPeriodo " +
            "ORDER BY a.fechaAsistencia DESC")
    List<Asistencia> obtenerAsistenciaPorGradoCursoPeriodo(@Param("idGrado") Long idGrado,
                                                      @Param("idCurso") Long idCurso,
                                                      @Param("idPeriodo") Long idPeriodo);


}
