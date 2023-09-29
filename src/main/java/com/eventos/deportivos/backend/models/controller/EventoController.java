package com.eventos.deportivos.backend.models.controller;

import com.eventos.deportivos.backend.models.entity.Equipo;
import com.eventos.deportivos.backend.models.entity.Evento;
import com.eventos.deportivos.backend.models.entity.Fase;
import com.eventos.deportivos.backend.models.service.ServiceEntity;
import com.eventos.deportivos.backend.models.service.ServiceEntityEntity;
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

@RestController
@RequestMapping("/eventos")
public class EventoController {

    @Autowired
    @Qualifier("evento")
    private ServiceEntity<Evento, Long> eventoService;

    @Autowired
    @Qualifier("equipo")
    private ServiceEntityEntity<Equipo, Long> equipoService;

    @Autowired
    @Qualifier("fase")
    private ServiceUnoAMuchos<Fase, Long> faseService;

    @GetMapping("/")
    public List<Evento> getEventos() {
        return eventoService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id ) {

        Evento evento = null;
        Map<String, Object> response = new HashMap<>();

        try {
            evento = eventoService.porId(id);

        } catch ( DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos." );
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (evento== null) {
            response.put("mensaje", "El evento ID: ".concat(id.toString().concat(" no existe en los registros.")) );
            return new ResponseEntity<Map<String, Object>> (response, HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<Evento> (evento, HttpStatus.OK);
    }


    @PostMapping("/")
    public ResponseEntity<?> crear(@Valid @RequestBody Evento evento , BindingResult result) {

        Evento eventoNuevo= null;
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            List<String> error = result.getFieldErrors()
                    .stream()
                    .map(er -> "El campo '" + er.getField() + "' "+ er.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errors", error);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
       /* if (eventoService.nombreExiste(evento.getNombreEvento())){
            response.put("mensaje", "El nombre evento ya existe en la base de datos." );
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }*/

        try {
            eventoNuevo = eventoService.guardar(evento);
        }catch (DataAccessException e){
            response.put("mensaje", "Error al realizar la inserccion en la base de datos." );
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "Evento creado con exito");
        response.put("evento", eventoNuevo);
        return new ResponseEntity<Map<String, Object> > (response, HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Evento evento, BindingResult result, @PathVariable Long id) {

        Evento actual = eventoService.porId(id);
        Evento nuevo= null;
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
            response.put("mensaje", "Error: no se pudo editar el evento ID: ".concat(id.toString().concat(" ya que no existe en los registros.")) );
            return new ResponseEntity<Map<String, Object>> (response, HttpStatus.NOT_FOUND);
        }
       /* if (eventoService.nombreExiste(evento.getNombreEvento())){
            response.put("mensaje", "El nombre evento ya existe en la base de datos." );
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }*/

        try {
            actual.setNombreEvento(evento.getNombreEvento());
            actual.setCategoria(evento.getCategoria());
            nuevo = eventoService.guardar(actual);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar el evento en la base de datos." );
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "Evento actualizada con exito");
        response.put("evento", nuevo);

        return new ResponseEntity<Map<String, Object> > (response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete (@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();

        try {
            Evento evento = eventoService.porId(id);

            if (evento !=null) {
                eventoService.eliminar(id);
                response.put("mensaje", "Evento eliminado con exito.");
                return new ResponseEntity<Map<String, Object> > (response, HttpStatus.OK);
            }

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al eliminar evento en la base de datos." );
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "No se encontro el evento para eliminar.");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    // EQUIPOS QUE TIENE UN EVENTO
    @GetMapping("equipos/{id}")
    public  ResponseEntity<?> equipoEventos(@PathVariable Long id ) {

        List<Equipo> listaEquipos = null;
        Map<String, Object> response = new HashMap<>();

        try {
            listaEquipos = equipoService.listarByEntity(id);

        } catch ( DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos." );
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (listaEquipos== null || listaEquipos.isEmpty()) {
            response.put("mensaje", "No hay equipos para el evento.");
            return new ResponseEntity<Map<String, Object>> (response, HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<>(listaEquipos, HttpStatus.OK);
    }

    // FASES QUE TIENE UN EVENTO
    @GetMapping("fases/{id}")
    public  ResponseEntity<?> fasesEventos(@PathVariable Long id ) {

        Map<String, Object> response = new HashMap<>();

        List<Fase> listaFases = null;
        try {
            listaFases = faseService.listarEntitysByEntity(id);

        } catch ( DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos." );
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (listaFases== null || listaFases.isEmpty()) {
            response.put("mensaje", "No hay fases para el evento.");
            return new ResponseEntity<Map<String, Object>> (response, HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<>(listaFases, HttpStatus.OK);
    }
}
