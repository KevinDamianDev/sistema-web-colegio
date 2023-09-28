package com.eventos.deportivos.backend.models.controller;

import com.eventos.deportivos.backend.models.entity.Persona;
import com.eventos.deportivos.backend.models.entity.PersonaRol;
import com.eventos.deportivos.backend.models.entity.Rol;
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
@RequestMapping("/personarol")
public class PersonaRolController {

    @Autowired
    @Qualifier("personarol")
    private ServiceEntity<PersonaRol, Long> servicePersonarol;

    @Autowired
    @Qualifier("persona")
    private ServiceEntity<Persona, Long> personaService;

    @Autowired
    @Qualifier("rol")
    private ServiceEntity<Rol, Long> rolService;

    @Autowired
    @Qualifier("personarol")
    private ServiceEntityExisteDato<PersonaRol, Long> servicePersonarol2;

        @PostMapping("/")
        public ResponseEntity<?> crear( @RequestBody Map<String, Long> requestBody) {
        Long idPersona = requestBody.get("idPersona");
        Long idRol = requestBody.get("idRol");
            System.out.println(" ** idPersona** " + idPersona);
            System.out.println(" ** idRol** " + idRol);
        Persona persona= null;
        Rol rol= null;
        Map<String, Object> response = new HashMap<>();

        try {

            persona = personaService.porId(idPersona);
            rol = rolService.porId(idRol);

        }catch (DataAccessException e){
            response.put("mensaje", "Error al realizar la inserccion en la base de datos." );
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(persona == null) {
            response.put("mensaje", "La persona no existe en la base de datos." );
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        if(rol == null) {
            response.put("mensaje", "El rol no existe en la base de datos." );
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        PersonaRol personaRol = new PersonaRol();
        personaRol = servicePersonarol2.existeRegistro(idPersona, idRol);

        if (personaRol != null) {
            response.put("mensaje", "La persona ya tiene asignado el rol." );
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        PersonaRol personaRoln = new PersonaRol();
        personaRoln.setPersona(persona);
        personaRoln.setRol(rol);

        PersonaRol nueva = servicePersonarol.guardar(personaRoln);

        response.put("mensaje", "Rol asignado a persona.");
        response.put("personaRol", nueva);
        return new ResponseEntity<Map<String, Object> > (response, HttpStatus.CREATED);

    }


    @GetMapping("/")
    public List<PersonaRol> getRol() {
        return servicePersonarol.listar();
    }
    @DeleteMapping("/")
    public ResponseEntity<?> eliminar( @RequestBody Map<String, Long> requestBody) {
        Long idPersona = requestBody.get("idPersona");
        Long idRol = requestBody.get("idRol");
        System.out.println(" ** idPersona** " + idPersona);
        System.out.println(" ** idRol** " + idRol);
        Persona persona= null;
        Rol rol= null;
        Map<String, Object> response = new HashMap<>();

        try {

            persona = personaService.porId(idPersona);
            rol = rolService.porId(idRol);

        }catch (DataAccessException e){
            response.put("mensaje", "Error al realizar la inserccion en la base de datos." );
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(persona == null) {
            response.put("mensaje", "La persona no existe en la base de datos." );
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        if(rol == null) {
            response.put("mensaje", "El rol no existe en la base de datos." );
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        PersonaRol personaRol = new PersonaRol();
        personaRol = servicePersonarol2.existeRegistro(idPersona, idRol);

        if (personaRol != null) {
            servicePersonarol.eliminar(personaRol.getIdPersonaRol());
            response.put("mensaje", "El rol de la persona se ha eliminado." );
            return new ResponseEntity<Map<String, Object> > (response, HttpStatus.OK);
        }
        response.put("mensaje", "No existe rol a eliminar de la persona." );
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

    }

}
