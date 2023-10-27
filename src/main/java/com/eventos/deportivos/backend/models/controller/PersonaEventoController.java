package com.eventos.deportivos.backend.models.controller;

import com.eventos.deportivos.backend.models.entity.*;
import com.eventos.deportivos.backend.models.service.ServiceEntity;
import com.eventos.deportivos.backend.models.service.ServiceEntityEntity;
import com.eventos.deportivos.backend.models.service.ServiceEntityExisteDato;
import com.eventos.deportivos.backend.models.service.ServiceUnoAMuchos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/personaevento")
public class PersonaEventoController {

    @Autowired
    @Qualifier("personaevento")
    private ServiceEntity<PersonaEvento, Long> personaEventoService;

    @Autowired
    @Qualifier("personaevento")
    private ServiceUnoAMuchos<PersonaEvento, Long> personaEventoServiceEntityByEntity;

    @Autowired
    @Qualifier("persona")
    private ServiceEntity<Persona, Long> personaService;

    @Autowired
    @Qualifier("evento")
    private ServiceEntity<Evento, Long> eventoService;

    @Autowired
    @Qualifier("personaevento")
    private ServiceEntityExisteDato<PersonaEvento, Long> personaEventoService2;

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id ) {

        PersonaEvento personaEvento ;
        Map<String, Object> response = new HashMap<>();

        try {
            personaEvento = personaEventoService.porId(id);

        } catch ( DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos." );
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (personaEvento== null) {
            response.put("mensaje", "El registro no existe en la base de datos.");
            return new ResponseEntity<Map<String, Object>> (response, HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<PersonaEvento> (personaEvento, HttpStatus.OK);
    }


    @PostMapping("/")
    public ResponseEntity<?> crear(@RequestBody PersonaEvento personaEventoN) {
        Long idPersona = personaEventoN.getPersona().getIdPersona();
        Long idEvento = personaEventoN.getEvento().getIdEvento();

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


    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {


        Map<String, Object> response = new HashMap<>();

        try {
            PersonaEvento personaEvento = personaEventoService.porId(id);
            if (personaEvento !=null) {
                personaEventoService.eliminar(id);

                response.put("mensaje", "Evento eliminado de la persona con exito1.");
                return new ResponseEntity<Map<String, Object> > (response, HttpStatus.OK);
            }

        }catch (DataAccessException e){
            response.put("mensaje", "Error al eliminar el evento de la persona en la base de datos." );
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "No se encontro el evento asignado para eliminar.");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

    }


    // PERSONA_EVENTO QUE TIENE UNA PERSONA
    @GetMapping("personas/{id}")
    public  ResponseEntity<?>personaEventoByPersona(@PathVariable Long id ) {

        List<PersonaEvento> listaPersonasEventos;
        Map<String, Object> response = new HashMap<>();

        try {
            listaPersonasEventos = personaEventoServiceEntityByEntity.listarEntitysByEntity(id);

        } catch ( DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos." );
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (listaPersonasEventos== null || listaPersonasEventos.isEmpty()) {
            response.put("mensaje", "No hay eventos asignados a la persona.");
            return new ResponseEntity<Map<String, Object>> (response, HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<>(listaPersonasEventos, HttpStatus.OK);
    }
}
