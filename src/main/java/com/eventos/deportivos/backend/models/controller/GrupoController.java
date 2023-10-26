package com.eventos.deportivos.backend.models.controller;

import com.eventos.deportivos.backend.models.entity.Equipo;
import com.eventos.deportivos.backend.models.entity.EquipoEstadistica;
import com.eventos.deportivos.backend.models.entity.Grupo;
import com.eventos.deportivos.backend.models.service.ServiceEntity;
import com.eventos.deportivos.backend.models.service.ServiceEntityEntity;
import com.eventos.deportivos.backend.models.service.ServiceEquipoGrupo;
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
@RequestMapping("/grupos")
public class GrupoController {

    @Autowired
    @Qualifier("grupo")
    private ServiceEntity<Grupo, Long> grupoService;

    @Autowired
    @Qualifier("equipo")
    private ServiceEntityEntity<Equipo, Long> equipoService;

    @Autowired
    @Qualifier("equipo")
    private ServiceEquipoGrupo<Equipo,Long> equipoGrupoService;

    @Autowired
    @Qualifier("equipoestadistica")
    private ServiceEntityEntity<EquipoEstadistica, Long> equipoEstadisticaService;

    @GetMapping("/")
    public List<Grupo> getGrupo() {
        return grupoService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id ) {

        Grupo grupo;
        Map<String, Object> response = new HashMap<>();

        try {
            grupo = grupoService.porId(id);
        } catch ( DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos." );
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (grupo== null) {
            response.put("mensaje", "El grupo ID: ".concat(id.toString().concat(" no existe en los registros.")) );
            return new ResponseEntity<Map<String, Object>> (response, HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<Grupo> (grupo, HttpStatus.OK);
    }


    @PostMapping("/")
    public ResponseEntity<?> crear(@Valid @RequestBody Grupo grupo , BindingResult result) {

        Grupo grupoNuevo;
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
            grupoNuevo = grupoService.guardar(grupo);
        }catch (DataAccessException e){
            response.put("mensaje", "Error al realizar la inserccion en la base de datos." );
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "Grupo creado con exito");
        response.put("grupo", grupoNuevo);
        return new ResponseEntity<Map<String, Object> > (response, HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Grupo grupo, BindingResult result, @PathVariable Long id) {


        Grupo actual = grupoService.porId(id);
        Grupo nuevo= null;
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
            response.put("mensaje", "Error: no se pudo editar el grupo ID: ".concat(id.toString().concat(" ya que no existe en los registros.")) );
            return new ResponseEntity<Map<String, Object>> (response, HttpStatus.NOT_FOUND);
        }

        try {
            actual.setNombreGrupo(grupo.getNombreGrupo());
            actual.setFase(grupo.getFase());
            nuevo = grupoService.guardar(actual);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar el grupo en la base de datos." );
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "Grupo actualizado con exito");
        response.put("grupo", nuevo);

        return new ResponseEntity<Map<String, Object> > (response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete (@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();

        try {
            Grupo grupo = grupoService.porId(id);

            if (grupo !=null) {
                grupoService.eliminar(id);
                response.put("mensaje", "Grupo eliminado con exito.");
                return new ResponseEntity<Map<String, Object> > (response, HttpStatus.OK);
            }

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al eliminar el grupo en la base de datos." );
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "No se encontro el grupo para eliminar.");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    //MUESTRA LOS EQUIPO QUE PERTENECEN A UN GRUPO
    @GetMapping("equipos/{id}")
    public  ResponseEntity<?> equipoGrupos(@PathVariable Long id ) {

        List<Equipo> listaEquipos;
        Map<String, Object> response = new HashMap<>();

        try {
            listaEquipos = equipoGrupoService.listarEquipoByGrupo(id);

        } catch ( DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos." );
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (listaEquipos== null || listaEquipos.isEmpty()) {
            response.put("mensaje", "No hay equipos para el grupo.");
            return new ResponseEntity<Map<String, Object>> (response, HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<>(listaEquipos, HttpStatus.OK);
    }

    //MUESTRA LAS ESTADISTICAS DE EQUIPOS QUE PERTENECEN A UN GRUPO
    @GetMapping("estadisticas/{id}")
    public  ResponseEntity<?> estadisticasEquipoGrupos(@PathVariable Long id ) {

        List<EquipoEstadistica> listas;
        Map<String, Object> response = new HashMap<>();

        try {
            listas = equipoEstadisticaService.listarByEntity(id);
        } catch ( DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos." );
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (listas== null || listas.isEmpty()) {
            response.put("mensaje", "No hay estadisticas de equipos para el grupo.");
            return new ResponseEntity<Map<String, Object>> (response, HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<>(listas, HttpStatus.OK);
    }
}
