package com.eventos.deportivos.backend.models.dao.colegio;

import com.eventos.deportivos.backend.models.entity.colegio.PersonaRolC;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface IPersonaRolCDao extends CrudRepository<PersonaRolC, Long> {

    @Query("SELECT pr FROM PersonaRolC pr WHERE pr.persona.idPersona = :idPersona AND pr.rol.idRol = :idRol")
    PersonaRolC buscarPorIdPersonaYIdRol(@Param("idPersona") Long idPersona, @Param("idRol") Long idRol);

}
