package com.eventos.deportivos.backend.models.dao;

import com.eventos.deportivos.backend.models.entity.PersonaRol;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface IPersonaRolDao extends CrudRepository<PersonaRol, Long> {

    @Query("SELECT pr FROM PersonaRol pr WHERE pr.persona.idPersona = :idPersona AND pr.rol.idRol = :idRol")
    PersonaRol buscarPorIdPersonaYIdRol(@Param("idPersona") Long idPersona, @Param("idRol") Long idRol);
}
