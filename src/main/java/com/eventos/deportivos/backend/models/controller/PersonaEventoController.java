package com.eventos.deportivos.backend.models.controller;

import com.eventos.deportivos.backend.models.entity.Evento;
import com.eventos.deportivos.backend.models.entity.Persona;
import com.eventos.deportivos.backend.models.entity.PersonaEvento;
import com.eventos.deportivos.backend.models.service.ServiceEntity;
import com.eventos.deportivos.backend.models.service.ServiceEntityExisteDato;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/personaevento")
public class PersonaEventoController {

    @Autowired
    @Qualifier("personaevento")
    private ServiceEntity<PersonaEvento, Long> personaEventoService;

    @Autowired
    @Qualifier("persona")
    private ServiceEntity<Persona, Long> personaService;

    @Autowired
    @Qualifier("evento")
    private ServiceEntity<Evento, Long> eventoService;

    @Autowired
    @Qualifier("personaevento")
    private ServiceEntityExisteDato<PersonaEvento, Long> personaEventoService2;

    @PostMapping("/")
    public ResponseEntity<?> crear(@RequestBody Map<String, Long> requestBody) {
        Long idPersona = requestBody.get("idPersona");
        Long idEvento = requestBody.get("idEvento");

        Persona persona= null;
        Evento evento= null;
        Map<String, Object> response = new HashMap<>();

        try {

            persona = personaService.porId(idPersona);
            evento = eventoService.porId(idEvento);

        }catch (DataAccessException e){
            response.put("mensaje", "Error al realizar la inserccion en la base de datos." );
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(persona == null) {
            response.put("mensaje", "La persona no existe en la base de datos." );
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        if(evento == null) {
            response.put("mensaje", "El evento no existe en la base de datos." );
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        PersonaEvento personaEvento = new PersonaEvento();
        personaEvento = personaEventoService2.existeRegistro(idPersona, idEvento);

        if (personaEvento != null) {
            response.put("mensaje", "La persona ya tiene asignado el evento." );
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        PersonaEvento personaEventoNuevo = new PersonaEvento();
        personaEventoNuevo.setPersona(persona);
        personaEventoNuevo.setEvento(evento);

        PersonaEvento nueva = personaEventoService.guardar(personaEventoNuevo);

        response.put("mensaje", "Evento "+ nueva.getEvento().getNombreEvento() + " asignado a la persona "+ nueva.getPersona().getNombrePersona());
        response.put("PersonaEvento", nueva);
        return new ResponseEntity<Map<String, Object> > (response, HttpStatus.CREATED);

    }


    @GetMapping("/")
    public List<PersonaEvento> getEvento() {
        return personaEventoService.listar();
    }
    @DeleteMapping("/")
    public ResponseEntity<?> eliminar( @RequestBody Map<String, Long> requestBody) {
        Long idPersona = requestBody.get("idPersona");
        Long idEvento = requestBody.get("idEvento");

        Persona persona= null;
        Evento evento= null;
        Map<String, Object> response = new HashMap<>();

        try {

            persona = personaService.porId(idPersona);
            evento = eventoService.porId(idEvento);

        }catch (DataAccessException e){
            response.put("mensaje", "Error al realizar la inserccion en la base de datos." );
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(persona == null) {
            response.put("mensaje", "La persona no existe en la base de datos." );
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        if(evento == null) {
            response.put("mensaje", "El evento no existe en la base de datos." );
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        PersonaEvento personaEvento = new PersonaEvento();
        personaEvento = personaEventoService2.existeRegistro(idPersona, idEvento);

        if (personaEvento != null) {
            personaEventoService.eliminar(personaEvento.getIdPersonaEvento());
            response.put("mensaje", "El evento de la persona se ha eliminado." );
            return new ResponseEntity<Map<String, Object> > (response, HttpStatus.OK);
        }
        response.put("mensaje", "No existe evento a eliminar de la persona." );
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

    }


}
