package com.eventos.deportivos.backend.models.controller;

import com.eventos.deportivos.backend.models.entity.Equipo;
import com.eventos.deportivos.backend.models.entity.EquipoJugador;
import com.eventos.deportivos.backend.models.entity.Jugador;
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
@RequestMapping("/equipojugador")
public class EquipoJugadorController {

    @Autowired
    @Qualifier("equipojugador")
    private ServiceEntity<EquipoJugador, Long> equipoJugadorService;

    @Autowired
    @Qualifier("equipo")
    private ServiceEntity<Equipo, Long> equipoService;

    @Autowired
    @Qualifier("jugador")
    private ServiceEntity<Jugador, Long> jugadorService;

    @Autowired
    @Qualifier("equipojugador")
    private ServiceEntityExisteDato<EquipoJugador, Long> equipoJugadorService2;

    @GetMapping("/")
    public List<EquipoJugador> getEquipoJugador() {
        return equipoJugadorService.listar();
    }

    @PostMapping("/")
    public ResponseEntity<?> crear(@RequestBody Map<String, Long> requestBody) {

        Long idEquipo= requestBody.get("idEquipo");
        Long idJugador = requestBody.get("idJugador");
        System.out.println("************idJugador: **********" + idJugador);
        System.out.println("*************idEquipo: *********" + idEquipo);

        Jugador jugador= null;
        Equipo equipo= null;
        Map<String, Object> response = new HashMap<>();

        try {
            jugador = jugadorService.porId(idJugador);
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
        if(jugador == null) {
            response.put("mensaje", "El jugador no existe en la base de datos." );
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        EquipoJugador equipoJugador = new EquipoJugador();
        equipoJugador = equipoJugadorService2.existeRegistro(idEquipo, idJugador);

        if (equipoJugador != null) {
            response.put("mensaje", "El equipo ya tiene asignado el jugador." );
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        EquipoJugador equipoJugadorNuevo = new EquipoJugador();
        equipoJugadorNuevo.setEquipo(equipo);
        equipoJugadorNuevo.setJugador(jugador);

        EquipoJugador nuevo = equipoJugadorService.guardar(equipoJugadorNuevo);

        response.put("mensaje", "Jugador "+ nuevo.getJugador().getNombreJugador() + " asignado al equipo "+ nuevo.getEquipo().getNombreEquipo());
        response.put("EquipoJugador", nuevo);
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }


    @DeleteMapping("/")
    public ResponseEntity<?> eliminar( @RequestBody Map<String, Long> requestBody) {
        Long idJugador = requestBody.get("idJugador");
        Long idEquipo= requestBody.get("idEquipo");

        Jugador jugador;
        Equipo equipo;
        Map<String, Object> response = new HashMap<>();

        try {
            jugador = jugadorService.porId(idJugador);
            equipo = equipoService.porId(idEquipo);

        }catch (DataAccessException e){
            response.put("mensaje", "Error al realizar la inserccion en la base de datos." );
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(equipo == null) {
            response.put("mensaje", "El equipo no existe en la base de datos." );
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        if(jugador == null) {
            response.put("mensaje", "El jugador no existe en la base de datos." );
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        EquipoJugador equipoJugador = new EquipoJugador();
        equipoJugador = equipoJugadorService2.existeRegistro(idEquipo, idJugador);

        if (equipoJugador != null) {
            equipoJugadorService.eliminar(equipoJugador.getIdEquipoJugador());

            response.put("mensaje", "El jugador se ha eliminado del equipo." );
            return new ResponseEntity<Map<String, Object> > (response, HttpStatus.OK);
        }
        response.put("mensaje", "No existe jugador a eliminar del equipo." );
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

    }

}
