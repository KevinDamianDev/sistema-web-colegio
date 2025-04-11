package com.eventos.deportivos.backend.models.controller.colegio;

import com.eventos.deportivos.backend.models.entity.colegio.PersonaC;
import com.eventos.deportivos.backend.models.entity.colegio.PersonaRolC;
import com.eventos.deportivos.backend.models.entity.colegio.RolC;
import com.eventos.deportivos.backend.models.service.colegio.ServiceEntityC;
import com.eventos.deportivos.backend.models.service.colegio.ServiceExisteDato;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/ie/personarol")
public class PersonRolCController {

    @Autowired
    @Qualifier("personarol")
    private ServiceEntityC<PersonaRolC, Long> personarolService;

    @Autowired
    @Qualifier("personarol")
    private ServiceExisteDato<PersonaRolC, Long> personarolService2;

    @Autowired
    @Qualifier("persona")
    private ServiceEntityC<PersonaC, Long> personaService;

    @Autowired
    @Qualifier("rol")
    private ServiceEntityC<RolC, Long> rolService;

    @GetMapping("/")
    //@Secured({"ROLE_ADMIN"})
    public List<PersonaRolC> getAll() {
        return personarolService.listar();
    }

    @PostMapping("/")
    //@Secured({"ROLE_ADMIN"})
    public ResponseEntity<?> crear( @RequestBody Map<String, Long> requestBody) {

        Long idPersona = requestBody.get("idPersona");
        Long idRol = requestBody.get("idRol");
        PersonaC persona= null;
        RolC rol= null;
        Map<String, Object> response = new HashMap<>();


        try {
            persona = personaService.porId(idPersona);
            rol = rolService.porId(idRol);

        }catch (DataAccessException e){
            response.put("mensaje", "Error al realizar la consulta en la base de datos." );
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


        PersonaRolC personaRol = new PersonaRolC();
        personaRol = personarolService2.existeRegistro(idPersona, idRol);
        if (personaRol != null) {
            response.put("mensaje", "La persona ya tiene asignado el rol." );
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        PersonaRolC personaRoln = new PersonaRolC();
        personaRoln.setPersona(persona);
        personaRoln.setRol(rol);

        PersonaRolC nueva = personarolService.guardar(personaRoln);

        response.put("mensaje", "Rol asignado a persona.");
        response.put("personaRol", nueva);
        return new ResponseEntity<Map<String, Object> > (response, HttpStatus.CREATED);

    }


    @DeleteMapping("/")
    public ResponseEntity<?> eliminar( @RequestBody Map<String, Long> requestBody) {
        Long idPersona = requestBody.get("idPersona");
        Long idRol = requestBody.get("idRol");
        System.out.println(" ** idPersona** " + idPersona);
        System.out.println(" ** idRol** " + idRol);
        PersonaC persona= null;
        RolC rol= null;
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

        PersonaRolC personaRol = new PersonaRolC();
        personaRol = personarolService2.existeRegistro(idPersona, idRol);

        if (personaRol != null) {
            personarolService.eliminar(personaRol.getIdPersonaRol());
            response.put("mensaje", "El rol de la persona se ha eliminado." );
            return new ResponseEntity<Map<String, Object> > (response, HttpStatus.OK);
        }
        response.put("mensaje", "No existe rol a eliminar de la persona." );
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

    }
}
