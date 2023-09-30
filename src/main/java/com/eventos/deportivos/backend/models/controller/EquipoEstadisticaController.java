package com.eventos.deportivos.backend.models.controller;

import com.eventos.deportivos.backend.models.entity.Equipo;
import com.eventos.deportivos.backend.models.entity.EquipoEstadistica;
import com.eventos.deportivos.backend.models.entity.Evento;
import com.eventos.deportivos.backend.models.service.ServiceEntity;
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

@RestController
@RequestMapping("/equipoEstadisticas")
public class EquipoEstadisticaController {

    @Autowired
    @Qualifier("equipoestadistica")
    private ServiceEntity<EquipoEstadistica, Long> equipoEstadisticaService;

    @GetMapping("/")
    public List<EquipoEstadistica> getEquipoEstadistica() {
        return equipoEstadisticaService.listar();
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id ) {

        EquipoEstadistica equipoEstadistica;
        Map<String, Object> response = new HashMap<>();

        try {
            equipoEstadistica = equipoEstadisticaService.porId(id);

        } catch ( DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos." );
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (equipoEstadistica== null) {
            response.put("mensaje", "No existe informacion en los registros.");
            return new ResponseEntity<Map<String, Object>> (response, HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<EquipoEstadistica> (equipoEstadistica, HttpStatus.OK);
    }


    @PostMapping("/")
    public ResponseEntity<?> crear(@Valid @RequestBody EquipoEstadistica equipoEstadistica , BindingResult result) {

        EquipoEstadistica equipoEstadisticaNuevo;
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
            equipoEstadisticaNuevo = equipoEstadisticaService.guardar(equipoEstadistica);

        }catch (DataAccessException e){
            response.put("mensaje", "Error al realizar la inserccion en la base de datos." );
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "Estadistica creada con exito");
        response.put("EquipoEstadistica", equipoEstadisticaNuevo);
        return new ResponseEntity<Map<String, Object> > (response, HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody EquipoEstadistica equipoEstadistica, BindingResult result, @PathVariable Long id) {

        EquipoEstadistica actual = equipoEstadisticaService.porId(id);

        EquipoEstadistica nuevo;
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
            response.put("mensaje", "Error: no se pudo editar ya que no existen en los registros.");
            return new ResponseEntity<Map<String, Object>> (response, HttpStatus.NOT_FOUND);
        }

        try {
            actual.setEmpatados(equipoEstadistica.getEmpatados());
            actual.setGanados(equipoEstadistica.getGanados());
            actual.setGolesContra(equipoEstadistica.getGolesContra());
            actual.setGolesFavor(equipoEstadistica.getGolesFavor());
            actual.setJugados(equipoEstadistica.getJugados());
            actual.setPerdidos(equipoEstadistica.getPerdidos());
            actual.setPuntos(equipoEstadistica.getPuntos());
            nuevo = equipoEstadisticaService.guardar(actual);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar las estadisticas en la base de datos." );
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "Estadisticas actualizada con exito");
        response.put("EquipoEstadistica", nuevo);

        return new ResponseEntity<Map<String, Object> > (response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete (@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();

        try {
            EquipoEstadistica equipoEstadistica = equipoEstadisticaService.porId(id);

            if (equipoEstadistica !=null) {
                equipoEstadisticaService.eliminar(id);
                response.put("mensaje", "Estadisticas eliminadas con exito.");
                return new ResponseEntity<Map<String, Object> > (response, HttpStatus.OK);
            }

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al eliminar estadisticas en la base de datos." );
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "No se encontro estadisticas para eliminar.");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
