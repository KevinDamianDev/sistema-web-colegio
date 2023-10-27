package com.eventos.deportivos.backend.models.dao;

import com.eventos.deportivos.backend.models.entity.EquipoJugador;
import com.eventos.deportivos.backend.models.entity.PersonaEvento;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IPersonaEventoDao extends CrudRepository<PersonaEvento, Long> {
    @Query("SELECT pr FROM PersonaEvento pr WHERE pr.persona.idPersona = :idPersona AND pr.evento.idEvento = :idEvento")
    PersonaEvento buscarPorIdPersonaYIdEvento(@Param("idPersona") Long idPersona, @Param("idEvento") Long idEvento);

    @Query("SELECT d FROM PersonaEvento d WHERE d.persona.idPersona= :idPersona")
    List<PersonaEvento> getPersonaEventoByPersona(@Param("idPersona") Long idPersona);

}
