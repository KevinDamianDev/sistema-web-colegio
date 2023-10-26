package com.eventos.deportivos.backend.models.controller;

import com.eventos.deportivos.backend.models.entity.Duelo;
import com.eventos.deportivos.backend.models.entity.EquipoGrupoDuelo;
import com.eventos.deportivos.backend.models.entity.Gol;
import com.eventos.deportivos.backend.models.service.ServiceEntity;
import com.eventos.deportivos.backend.models.service.ServiceEntityEntity;
import com.eventos.deportivos.backend.models.service.ServiceUnoAMuchos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/duelos")
public class DueloController {

    @Autowired
    @Qualifier("duelo")
    private ServiceEntity<Duelo, Long> dueloService;

    @Autowired
    @Qualifier("duelo")
    private ServiceEntityEntity<Duelo, Long> dueloEntityService;

    @Autowired
    @Qualifier("equipogrupoduelo")
    private ServiceUnoAMuchos<EquipoGrupoDuelo, Long> equipoGrupoDueloService;

    @Autowired
    @Qualifier("gol")
    private ServiceUnoAMuchos<Gol, Long> golService;

    @GetMapping("/")
    public List<Duelo> getDuelo() {
        return dueloService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id ) {

        Duelo duelo;
        Map<String, Object> response = new HashMap<>();

        try {
            duelo = dueloService.porId(id);
        } catch ( DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos." );
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (duelo== null) {
            response.put("mensaje", "El duelo ID: ".concat(id.toString().concat(" no existe en los registros.")) );
            return new ResponseEntity<Map<String, Object>> (response, HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<Duelo> (duelo, HttpStatus.OK);
    }


    @PostMapping("/")
    public ResponseEntity<?> crear(@Valid @RequestBody Duelo duelo , BindingResult result) {

        Duelo dueloNuevo;
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
            dueloNuevo = dueloService.guardar(duelo);
        }catch (DataAccessException e){
            response.put("mensaje", "Error al realizar la inserccion en la base de datos." );
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "Duelo creado con exito");
        response.put("duelo", dueloNuevo);
        return new ResponseEntity<Map<String, Object> > (response, HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    //@Secured({"ROLE_ADMIN","ROLE_USER"})
    public ResponseEntity<?> update(@Valid @RequestBody Duelo duelo, BindingResult result, @PathVariable Long id) {
        System.out.println("update ");

        Duelo actual = dueloService.porId(id);
        Duelo nuevo= null;
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
            response.put("mensaje", "Error: no se pudo editar el duelo ID: ".concat(id.toString().concat(" ya que no existe en los registros.")) );
            return new ResponseEntity<Map<String, Object>> (response, HttpStatus.NOT_FOUND);
        }

        try {
            actual.setFechaDuelo(duelo.getFechaDuelo());
            actual.setNombreDuelo(duelo.getNombreDuelo());
            actual.setLugarDuelo(duelo.getLugarDuelo());
            actual.setFecha(duelo.getFecha());
            actual.setEstado(duelo.getEstado());
            nuevo = dueloService.guardar(actual);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar el duelo en la base de datos." );
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "Duelo actualizado con exito");
        response.put("duelo", nuevo);

        return new ResponseEntity<Map<String, Object> > (response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete (@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();

        try {
            Duelo duelo = dueloService.porId(id);

            if (duelo !=null) {
                dueloService.eliminar(id);
                response.put("mensaje", "Duelo eliminado con exito.");
                return new ResponseEntity<Map<String, Object> > (response, HttpStatus.OK);
            }

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al eliminar el duelo en la base de datos." );
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "No se encontro el duelo para eliminar.");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    // EQUIPOS QUE TIENE UN DUELO
    @GetMapping("equipos/{id}")
    public  ResponseEntity<?> equipoDuelo(@PathVariable Long id ) {

        List<EquipoGrupoDuelo> listaEquipos = null;
        Map<String, Object> response = new HashMap<>();

        try {
            listaEquipos = equipoGrupoDueloService.listarEntitysByEntity(id);

        } catch ( DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos." );
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (listaEquipos== null || listaEquipos.isEmpty()) {
            response.put("mensaje", "No hay equipos para el duelo.");
            return new ResponseEntity<Map<String, Object>> (response, HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<>(listaEquipos, HttpStatus.OK);
    }
    // GOLES QUE TIENE UN DUELO
    @GetMapping("goles/{id}")
    public  ResponseEntity<?>golesDuelo(@PathVariable Long id ) {

        List<Gol> listaGol;
        Map<String, Object> response = new HashMap<>();

        try {
            listaGol = golService.listarEntitysByEntity(id);

        } catch ( DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos." );
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (listaGol== null || listaGol.isEmpty()) {
            response.put("mensaje", "No hay goles para el duelo.");
            return new ResponseEntity<Map<String, Object>> (response, HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<>(listaGol, HttpStatus.OK);
    }


    // DUELOS QUE TIENE UNA FASE
    @GetMapping("fases/{id}")
    public  ResponseEntity<?>duelosByFase(@PathVariable Long id ) {

        List<Duelo> listaDuelos;
        Map<String, Object> response = new HashMap<>();

        try {
            listaDuelos = dueloEntityService.listarByEntity(id);

        } catch ( DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos." );
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (listaDuelos== null || listaDuelos.isEmpty()) {
            response.put("mensaje", "No hay duelos para la fase.");
            return new ResponseEntity<Map<String, Object>> (response, HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<>(listaDuelos, HttpStatus.OK);
    }

}
