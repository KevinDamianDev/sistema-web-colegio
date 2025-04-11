package com.eventos.deportivos.backend.models.controller.colegio;

import com.eventos.deportivos.backend.models.dao.colegio.IPersonaCDao;
import com.eventos.deportivos.backend.models.entity.colegio.Curso;
import com.eventos.deportivos.backend.models.entity.colegio.PersonaC;
import com.eventos.deportivos.backend.models.entity.colegio.RolC;
import com.eventos.deportivos.backend.models.service.colegio.ServiceEntityC;
import com.eventos.deportivos.backend.models.service.colegio.ServiceListEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/ie/personac")
public class PersonaCController {

    @Autowired
    @Qualifier("persona")
    private ServiceEntityC<PersonaC, Long> personaService;

    @Autowired
    @Qualifier("persona")
    private ServiceListEntity<PersonaC, Long> personaServiceList;

    @Autowired
    private BCryptPasswordEncoder bcryptPasswordEncoder;

    @Autowired
    @Qualifier("rol")
    private ServiceEntityC<RolC, Long> rolService;

    @Autowired
    private IPersonaCDao personaCD;

    @GetMapping("/")
    //@Secured({"ROLE_ADMIN"})
    public List<PersonaC> getAll() {
        return personaService.listar();
    }
    // LISTA DE DOCENTES
    @GetMapping("/docentes")
    public List<PersonaC> getAllDocentes() {
        return personaServiceList.listarData();
    }

    // LISTA DE ALUMNOS
    @GetMapping("/alumnos")
    public List<PersonaC> getAllAlumnos() {
        return (List<PersonaC>)personaCD.findAllAlumnos();
    }

    @GetMapping("/{id}")
    //@Secured({"ROLE_ADMIN"})
    public ResponseEntity<?> show(@PathVariable Long id ) {

        PersonaC persona = null;
        Map<String, Object> response = new HashMap<>();

        try {
            persona = personaService.porId(id);
        } catch ( DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos." );
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (persona== null) {
            response.put("mensaje", "El registro ID: ".concat(id.toString().concat(" no existe en los registros.")) );
            return new ResponseEntity<Map<String, Object>> (response, HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<PersonaC> (persona, HttpStatus.OK);
    }

    @PostMapping("/")
    //@Secured({"ROLE_ADMIN"})
    public ResponseEntity<?> crear(@Valid @RequestBody PersonaC persona, BindingResult result) {

        PersonaC personaNueva= null;
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

            String password = persona.getPasswordPersona();
            System.out.println("password al crear persona, sin encriptar : " + password);
            String passwordBcrypt = bcryptPasswordEncoder.encode(password);
            System.out.println("password al crear persona, encriptada : " + passwordBcrypt);
            persona.setPasswordPersona(passwordBcrypt);

            personaNueva = personaService.guardar(persona);

            Set<RolC> roles = new HashSet<>();
            for (RolC rol : persona.getRoles()) {
                RolC rolAdmin = rolService.porId(rol.getIdRol());
                roles.add(rolAdmin);
            }

            personaNueva.setRoles(roles);
            personaService.guardar(personaNueva);

        }catch (DataAccessException e){
            response.put("mensaje", "Error al realizar la inserccion en la base de datos." );
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "Registro agregado.");
        response.put("persona",personaNueva);
        return new ResponseEntity<Map<String, Object> > (response, HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    //@Secured({"ROLE_ADMIN"})
    public ResponseEntity<?> update(@Valid @RequestBody PersonaC persona, BindingResult result, @PathVariable Long id) {

        PersonaC actual = personaService.porId(id);
        PersonaC nuevo= null;
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
            response.put("mensaje", "Error: no se pudo editar el registro ID: ".concat(id.toString().concat(" ya que no existe en los registros.")) );
            return new ResponseEntity<Map<String, Object>> (response, HttpStatus.NOT_FOUND);
        }

        try {
            actual.setNombrePersona(persona.getNombrePersona());
            actual.setApellidosPersonas(persona.getApellidosPersonas());
            actual.setDniPersona(persona.getDniPersona());
            actual.setTelefonoPersona(persona.getTelefonoPersona());
            actual.setFechaNacPersona(persona.getFechaNacPersona());
            actual.setCorreoPersona(persona.getCorreoPersona());
            actual.setUsuarioPersona(persona.getUsuarioPersona());

            actual.setFechaNacPersona(persona.getFechaNacPersona());
            actual.setEstadoPersona(persona.isEstadoPersona());

            // Actualiza los roles de la persona
            Set<RolC> roles = new HashSet<>();
            for (RolC rol : persona.getRoles()) {
                RolC rolAdmin = rolService.porId(rol.getIdRol());
                roles.add(rolAdmin);
            }

            actual.setRoles(roles);


            nuevo = personaService.guardar(actual);


        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar el registro en la base de datos." );
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "Registro actualizado con exito");
        response.put("persona", nuevo);

        return new ResponseEntity<Map<String, Object> > (response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    //@Secured({"ROLE_ADMIN"})
    public ResponseEntity<?> delete (@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();

        try {
            PersonaC persona=personaService.porId(id);

            if (persona !=null) {
                personaService.eliminar(id);
                response.put("mensaje", "Registro eliminado con exito.");
                return new ResponseEntity<Map<String, Object> > (response, HttpStatus.OK);
            }

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al eliminar el registro  en la base de datos." );
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "No se encontro el registro para eliminar.");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }



    @GetMapping("/alumnosgrado")
    public List<PersonaC> getAlumnosPeriodoPersonaGrado(
            @RequestParam Long idPersona, @RequestParam Long idGrado,
            @RequestParam Long idPeriodo) {

        return personaCD.findAlumnosByDocenteAndGradoAndPeriodo(idPersona, idGrado,idPeriodo);
    }
}
