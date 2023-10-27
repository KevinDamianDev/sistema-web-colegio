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
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
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
    private ServiceEntity<Rol, Long> rolService;

    @Autowired
    @Qualifier("evento")
    private ServiceEntityEntity<Evento, Long> eventoService;


    @GetMapping("/")
    //@Secured({"ROLE_ADMIN","ROLE_USER"})
    public List<Persona> getPersonas() {
        return personaService.listar();
    }

   @GetMapping("/{id}")
   //@Secured({"ROLE_ADMIN"})
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
//@Secured({"ROLE_ADMIN"})
    public ResponseEntity<?> crear(@Valid @RequestBody Persona persona, BindingResult result) {
        Persona personaNueva = null;
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(error -> "El campo '" + error.getField() + "' " + error.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            // Guarda la persona en la base de datos
            personaNueva = personaService.guardar(persona);

            // Obtén los roles administrados por JPA antes de asociarlos
            Set<Rol> roles = new HashSet<>();
            for (Rol rol : persona.getRoles()) {
                Rol rolAdmin = rolService.porId(rol.getIdRol());
                roles.add(rolAdmin);
            }

            personaNueva.setRoles(roles);
            personaService.guardar(personaNueva);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar la inserción en la base de datos.");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "Persona creada con éxito");
        response.put("persona", personaNueva);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    //@Secured({"ROLE_ADMIN"})
    public ResponseEntity<?> update(@Valid @RequestBody Persona persona, BindingResult result, @PathVariable Long id) {

        Persona actual = personaService.porId(id);
        Persona nuevo = null;
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            List<String> error = result.getFieldErrors()
                    .stream()
                    .map(er -> "El campo '" + er.getField() + "' " + er.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errors", error);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        if (actual == null) {
            response.put("mensaje", "Error: no se pudo editar a la persona ID: " + id + " ya que no existe en los registros.");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        try {
            actual.setNombrePersona(persona.getNombrePersona());
            actual.setApellidosPersonas(persona.getApellidosPersonas());
            actual.setCorreoPersona(persona.getCorreoPersona());
            actual.setDniPersona(persona.getDniPersona());
            actual.setEstadoPersona(persona.isEstadoPersona());
            actual.setFechaNacPersona(persona.getFechaNacPersona());
            actual.setPasswordPersona(persona.getPasswordPersona());

            // Actualiza los roles de la persona
            Set<Rol> roles = new HashSet<>();
            for (Rol rol : persona.getRoles()) {
                Rol rolAdmin = rolService.porId(rol.getIdRol());
                roles.add(rolAdmin);
            }

            actual.setRoles(roles);

            nuevo = personaService.guardar(actual);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar la persona en la base de datos.");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "Persona actualizada con éxito");
        response.put("persona", nuevo);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

   /* @PutMapping("/{id}")
    @Secured({"ROLE_ADMIN"})
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
            actual.setEstadoPersona(persona.isEstadoPersona());
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
    }*/

    @DeleteMapping("/{id}")
    @Secured({"ROLE_ADMIN"})
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

    /*// ROLES QUE TIENE UNA PERSONA
    @GetMapping("rol/{id}")
    @Secured({"ROLE_ADMIN"})
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
    }*/
    // EVENTOS QUE TIENE UNA PERSONA
    @GetMapping("evento/{id}")
    @Secured({"ROLE_ADMIN","ROLE_USER"})
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
