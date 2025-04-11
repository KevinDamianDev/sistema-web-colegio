package com.eventos.deportivos.backend.models.dao.colegio;

import com.eventos.deportivos.backend.models.entity.colegio.Grado;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IGradoDao extends CrudRepository<Grado, Long> {

    @Query("SELECT dg.grado " +
            "FROM DocenteGrado dg " +
            "WHERE dg.docente.persona.idPersona = :idPersona " +
            "AND dg.periodo.idPeriodo = :idPeriodo")
    List<Grado> findGradosByPeriodoPersona(@Param("idPersona") Long idPersona,
                                           @Param("idPeriodo") Long idPeriodo);

}
