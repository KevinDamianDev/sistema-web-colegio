package com.eventos.deportivos.backend.models.dao.colegio;

import com.eventos.deportivos.backend.models.entity.colegio.PersonaC;
import com.eventos.deportivos.backend.models.entity.colegio.RolC;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IPersonaCDao extends CrudRepository<PersonaC, Long> {

    @Query("SELECT pr.rol FROM PersonaRolC pr WHERE pr.persona.idPersona = :id")
    List<RolC> findRolesPorPersonaId(@Param("id") Long id);

    @Query("SELECT u from PersonaC u where u.usuarioPersona =:usuarioPersona")
    public PersonaC findUserName(@Param("usuarioPersona") String usuarioPersona);

    @Query("SELECT p FROM Docente d JOIN d.persona p")
    List<PersonaC> findAllDocentes();

    @Query("SELECT p FROM Alumno d JOIN d.persona p")
    List<PersonaC> findAllAlumnos();

    @Query("SELECT DISTINCT p FROM Alumno a " +
            "INNER JOIN a.persona p " +
            "INNER JOIN AlumnoGrado ag ON a.idAlumno = ag.alumno.idAlumno " +
            "INNER JOIN Grado g ON ag.grado.idGrado = g.idGrado " +
            "INNER JOIN DocenteGrado dg ON g.idGrado = dg.grado.idGrado " +
            "INNER JOIN Docente d ON dg.docente.idDocente = d.idDocente " +
            "INNER JOIN PersonaC dp ON d.persona.idPersona = dp.idPersona " +
            "WHERE dp.idPersona = :docenteId " +
            "AND g.idGrado = :gradoId " +
            "AND ag.periodo.idPeriodo = :periodoId")
    List<PersonaC> findAlumnosByDocenteAndGradoAndPeriodo(@Param("docenteId") Long docenteId,
                                                          @Param("gradoId") Long gradoId,
                                                          @Param("periodoId") Long periodoId);
}
