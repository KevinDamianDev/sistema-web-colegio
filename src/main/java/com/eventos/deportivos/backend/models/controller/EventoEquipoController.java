package com.eventos.deportivos.backend.models.controller;

import com.eventos.deportivos.backend.models.entity.*;
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
@RequestMapping("/eventoequipo")
public class EventoEquipoController {

    @Autowired
    @Qualifier("eventoequipo")
    private ServiceEntity<EventoEquipo, Long> eventoEquipoService;

    @Autowired
    @Qualifier("evento")
    private ServiceEntity<Evento, Long> eventoService;

    @Autowired
    @Qualifier("equipo")
    private ServiceEntity<Equipo, Long> equipoService;

    @Autowired
    @Qualifier("eventoequipo")
    private ServiceEntityExisteDato<EventoEquipo, Long> eventoEquipoService2;

    @GetMapping("/")
    public List<EventoEquipo> getEventoEquipo() {
        return eventoEquipoService.listar();
    }

    @PostMapping("/")
    public ResponseEntity<?> crear(@RequestBody Map<String, Long> requestBody) {

        Long idEvento = requestBody.get("idEvento");
        Long idEquipo= requestBody.get("idEquipo");
        System.out.println("************idEvento: **********" + idEvento);
        System.out.println("*************idEquipo: *********" + idEquipo);

        Evento evento= null;
        Equipo equipo= null;
        Map<String, Object> response = new HashMap<>();

        try {
            evento = eventoService.porId(idEvento);
            equipo = equipoService.porId(idEquipo);

        }catch (DataAccessException e){
            response.put("mensaje", "Error al realizar la inserccion en la base de datos." );
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(equipo == null) {
            response.put("mensaje", "El equipo no existe en la base de datos." );
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        if(evento == null) {
            response.put("mensaje", "El evento no existe en la base de datos." );
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        EventoEquipo eventoEquipo = new EventoEquipo();
        eventoEquipo = eventoEquipoService2.existeRegistro(idEvento, idEquipo);


        if (eventoEquipo != null) {
            response.put("mensaje", "El evento ya tiene asignado el equipo." );
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        EventoEquipo eventoEquipoNuevo = new EventoEquipo();
        eventoEquipoNuevo.setEquipo(equipo);
        eventoEquipoNuevo.setEvento(evento);

        EventoEquipo nuevo = eventoEquipoService.guardar(eventoEquipoNuevo);

        response.put("mensaje", "Equipo "+ nuevo.getEquipo().getNombreEquipo() + " asignado al evento "+ nuevo.getEvento().getNombreEvento());
        response.put("EventoEquipo", nuevo);
        return new ResponseEntity<Map<String, Object> > (response, HttpStatus.CREATED);

    }


    @DeleteMapping("/")
    public ResponseEntity<?> eliminar( @RequestBody Map<String, Long> requestBody) {
        Long idEvento = requestBody.get("idEvento");
        Long idEquipo= requestBody.get("idEquipo");

        Evento evento= null;
        Equipo equipo= null;
        Map<String, Object> response = new HashMap<>();

        try {
            evento = eventoService.porId(idEvento);
            equipo = equipoService.porId(idEquipo);

        }catch (DataAccessException e){
            response.put("mensaje", "Error al realizar la inserccion en la base de datos." );
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(equipo == null) {
            response.put("mensaje", "El equipo no existe en la base de datos." );
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        if(evento == null) {
            response.put("mensaje", "El evento no existe en la base de datos." );
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        EventoEquipo eventoEquipo = new EventoEquipo();
        eventoEquipo = eventoEquipoService2.existeRegistro(idEvento, idEquipo);

        if (eventoEquipo != null) {
            eventoEquipoService.eliminar(eventoEquipo.getIdEventoEquipo());

            response.put("mensaje", "El equipo se ha eliminado del evento." );
            return new ResponseEntity<Map<String, Object> > (response, HttpStatus.OK);
        }
        response.put("mensaje", "No existe equipo a eliminar del evento." );
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

    }

}
