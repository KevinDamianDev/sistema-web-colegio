package com.eventos.deportivos.backend.models.dao;

import com.eventos.deportivos.backend.models.entity.Persona;
import com.eventos.deportivos.backend.models.entity.Rol;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IPersonaDao extends CrudRepository<Persona, Long> {
    @Query("SELECT pr.rol FROM PersonaRol pr WHERE pr.persona.idPersona = :id")
    List<Rol> findRolesByPersonaId(@Param("id") Long id);
}
