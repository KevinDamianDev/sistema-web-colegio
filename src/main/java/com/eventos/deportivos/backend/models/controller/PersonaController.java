package com.eventos.deportivos.backend.models.controller;


import com.eventos.deportivos.backend.models.entity.Evento;
import com.eventos.deportivos.backend.models.entity.Persona;
import com.eventos.deportivos.backend.models.entity.Rol;
import com.eventos.deportivos.backend.models.service.ServiceEntity;
import com.eventos.deportivos.backend.models.service.ServiceEntityEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/personas")
public class PersonaController {

    @Autowired
    @Qualifier("persona")
    private ServiceEntity<Persona, Long> personaService;

    @Autowired
    @Qualifier("rol")
    private ServiceEntityEntity<Rol, Long> rolService;

    @Autowired
    @Qualifier("evento")
    private ServiceEntityEntity<Evento, Long> eventoService;


    @GetMapping("/")
    public List<Persona> getPersonas() {
        return personaService.listar();
    }

   @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id ) {

        Persona persona = null;
        Map<String, Object> response = new HashMap<>();

        try {
            persona = personaService.porId(id);

        } catch ( DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos." );
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (persona== null) {
            response.put("mensaje", "La persona ID: ".concat(id.toString().concat(" no existe en los registros.")) );
            return new ResponseEntity<Map<String, Object>> (response, HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<Persona> (persona, HttpStatus.OK);
    }


    @PostMapping("/")
    public ResponseEntity<?> crear(@Valid @RequestBody Persona persona , BindingResult result) {

        Persona personaNuevo= null;
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            List<String> error = result.getFieldErrors()
                    .stream()
                    .map(er -> "El campo '" + er.getField() + "' "+ er.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errors", error);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            personaNuevo = personaService.guardar(persona);
        }catch (DataAccessException e){
            response.put("mensaje", "Error al realizar la inserccion en la base de datos." );
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "Persona creada con exito");
        response.put("persona", personaNuevo);
        return new ResponseEntity<Map<String, Object> > (response, HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Persona persona, BindingResult result, @PathVariable Long id) {

        Persona actual = personaService.porId(id);
        Persona nuevo= null;
        Map<String, Object> response = new HashMap<>();
        //---------------MANEJO DE ERRORES--------
        if (result.hasErrors()) {
            List<String> error = result.getFieldErrors()
                    .stream()
                    .map(er -> "El campo '" + er.getField() + "' "+ er.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errors", error);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }


        if (actual== null) {
            response.put("mensaje", "Error: no se pudo editar a la persona ID: ".concat(id.toString().concat(" ya que no existe en los registros.")) );
            return new ResponseEntity<Map<String, Object>> (response, HttpStatus.NOT_FOUND);
        }

        try {
            actual.setNombrePersona(persona.getNombrePersona());
            actual.setApellidosPersonas(persona.getApellidosPersonas());
            actual.setCorreoPersona(persona.getCorreoPersona());
            actual.setDniPersona(persona.getDniPersona());
            actual.setEstadoPersona(persona.getEstadoPersona());
            actual.setFechaNacPersona(persona.getFechaNacPersona());
            actual.setPasswordPersona(persona.getPasswordPersona());
            actual.setPasswordPersona(persona.getPasswordPersona());
            nuevo = personaService.guardar(actual);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar la persona en la base de datos." );
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "Persona actualizada con exito");
        response.put("persona", nuevo);

        return new ResponseEntity<Map<String, Object> > (response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete (@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();

        try {
            Persona persona = personaService.porId(id);

            if (persona !=null) {
                personaService.eliminar(id);
                response.put("mensaje", "persona eliminada con exito.");
                return new ResponseEntity<Map<String, Object> > (response, HttpStatus.OK);
            }

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al eliminar persona en la base de datos." );
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "No se encontro la persona para eliminar.");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // ROLES QUE TIENE UNA PERSONA
    @GetMapping("rol/{id}")
    public  ResponseEntity<?> rolesPersona(@PathVariable Long id ) {

        List<Rol> listaRoles = null;
        Map<String, Object> response = new HashMap<>();

        try {
            listaRoles = rolService.listarByEntity(id);

        } catch ( DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos." );
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (listaRoles== null || listaRoles.isEmpty()) {
            response.put("mensaje", "No hay roles para el usuario.");
            return new ResponseEntity<Map<String, Object>> (response, HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<>(listaRoles, HttpStatus.OK);
    }
    // EVENTOS QUE TIENE UNA PERSONA
    @GetMapping("evento/{id}")
    public  ResponseEntity<?> eventosPersona(@PathVariable Long id ) {

        List<Evento> listaEventos = null;
        Map<String, Object> response = new HashMap<>();

        try {
            listaEventos = eventoService.listarByEntity(id);

        } catch ( DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos." );
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (listaEventos== null || listaEventos.isEmpty()) {
            response.put("mensaje", "No hay eventos para el usuario.");
            return new ResponseEntity<Map<String, Object>> (response, HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<>(listaEventos, HttpStatus.OK);
    }

}
