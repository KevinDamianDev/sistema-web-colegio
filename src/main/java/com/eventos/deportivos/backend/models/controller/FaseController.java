package com.eventos.deportivos.backend.models.controller;

import com.eventos.deportivos.backend.models.entity.Fase;
import com.eventos.deportivos.backend.models.entity.Fecha;
import com.eventos.deportivos.backend.models.entity.Grupo;
import com.eventos.deportivos.backend.models.service.ServiceEntity;
import com.eventos.deportivos.backend.models.service.ServiceUnoAMuchos;
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
@RequestMapping("/fases")
public class FaseController {

    @Autowired
    @Qualifier("fase")
    private ServiceEntity<Fase, Long> faseService;

    @Autowired
    @Qualifier("fecha")
    private ServiceUnoAMuchos<Fecha, Long> fechaService;

    @Autowired
    @Qualifier("grupo")
    private ServiceUnoAMuchos<Grupo, Long> grupoService;

    @GetMapping("/")
    public List<Fase> getFases() {
        return faseService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id ) {

        Fase fase;
        Map<String, Object> response = new HashMap<>();

        try {
            fase = faseService.porId(id);
        } catch ( DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos." );
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (fase== null) {
            response.put("mensaje", "La fase ID: ".concat(id.toString().concat(" no existe en los registros.")) );
            return new ResponseEntity<Map<String, Object>> (response, HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<Fase> (fase, HttpStatus.OK);
    }


    @PostMapping("/")
    public ResponseEntity<?> crear(@Valid @RequestBody Fase fase , BindingResult result) {

        Fase faseNuevo;
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
            faseNuevo = faseService.guardar(fase);
        }catch (DataAccessException e){
            response.put("mensaje", "Error al realizar la inserccion en la base de datos." );
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "Fase creada con exito");
        response.put("fase", faseNuevo);
        return new ResponseEntity<Map<String, Object> > (response, HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Fase fase, BindingResult result, @PathVariable Long id) {


        Fase actual = faseService.porId(id);
        Fase nuevo= null;
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
            response.put("mensaje", "Error: no se pudo editar la fase ID: ".concat(id.toString().concat(" ya que no existe en los registros.")) );
            return new ResponseEntity<Map<String, Object>> (response, HttpStatus.NOT_FOUND);
        }

        try {
            actual.setNombreFase(fase.getNombreFase());
            actual.setEvento(fase.getEvento());
            nuevo = faseService.guardar(actual);


        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar la fase en la base de datos." );
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "Fase actualizada con exito");
        response.put("fase", nuevo);

        return new ResponseEntity<Map<String, Object> > (response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete (@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();

        try {
            Fase fase = faseService.porId(id);

            if (fase !=null) {
                faseService.eliminar(id);
                response.put("mensaje", "Fase eliminada con exito.");
                return new ResponseEntity<Map<String, Object> > (response, HttpStatus.OK);
            }

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al eliminar la fase en la base de datos." );
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "No se encontro la fase para eliminar.");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    // FECHAS QUE TIENE UNA FASE
    @GetMapping("fechas/{id}")
    public  ResponseEntity<?> fechasFases(@PathVariable Long id ) {

        Map<String, Object> response = new HashMap<>();

        List<Fecha> listaFechas = null;
        try {
            listaFechas = fechaService.listarEntitysByEntity(id);

        } catch ( DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos." );
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (listaFechas== null || listaFechas.isEmpty()) {
            response.put("mensaje", "No hay fechas para la fase.");
            return new ResponseEntity<Map<String, Object>> (response, HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<>(listaFechas, HttpStatus.OK);
    }

    // GRUPOS QUE TIENE UNA FASE
    @GetMapping("grupos/{id}")
    public  ResponseEntity<?> gruposFases(@PathVariable Long id ) {

        Map<String, Object> response = new HashMap<>();

        List<Grupo> listaGrupo = null;
        try {
            listaGrupo = grupoService.listarEntitysByEntity(id);

        } catch ( DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos." );
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (listaGrupo== null || listaGrupo.isEmpty()) {
            response.put("mensaje", "No hay grupos para la Fase.");
            return new ResponseEntity<Map<String, Object>> (response, HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<>(listaGrupo, HttpStatus.OK);
    }
}
