package com.eventos.deportivos.backend.models.controller;

import com.eventos.deportivos.backend.models.entity.Equipo;
import com.eventos.deportivos.backend.models.entity.EquipoGrupo;
import com.eventos.deportivos.backend.models.entity.EquipoJugador;
import com.eventos.deportivos.backend.models.entity.Jugador;
import com.eventos.deportivos.backend.models.service.ServiceEntity;
import com.eventos.deportivos.backend.models.service.ServiceEntityEntity;
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
@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/equipojugador")
public class EquipoJugadorController {

    @Autowired
    @Qualifier("equipojugador")
    private ServiceEntity<EquipoJugador, Long> equipoJugadorService;

    @Autowired
    @Qualifier("equipojugador")
    private ServiceEntityEntity<EquipoJugador, Long> equipoJugadorServiceServiceEntity;

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
    public ResponseEntity<?> crear(@RequestBody EquipoJugador equipoJugadorN) {

        Long idEquipo= equipoJugadorN.getEquipo().getIdEquipo();
        Long idJugador = equipoJugadorN.getJugador().getIdJugador();
        System.out.println("************idJugador: **********" + idJugador);
        System.out.println("*************idEquipo: *********" + idEquipo);

        Jugador jugador= null;
        Equipo equipo= null;
        Map<String, Object> response = new HashMap<>();

        try {
            jugador = jugadorService.porId(idJugador);
            equipo = equipoService.porId(idEquipo);

        }catch (DataAccessException e){
            response.put("mensaje", "Error al realizar la inserccion en la base de datos jugador / eqiopo." );
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


    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {

        Map<String, Object> response = new HashMap<>();

        try {
            EquipoJugador ej = equipoJugadorService.porId(id);
            if (ej != null) {
                equipoJugadorService.eliminar(ej.getIdEquipoJugador());
                response.put("mensaje", "El jugador se ha eliminado del equipo." );
                return new ResponseEntity<Map<String, Object> > (response, HttpStatus.OK);
            }

        }catch (DataAccessException e){
            response.put("mensaje", "Error al realizar la inserccion en la base de datos." );
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "No se encontro el registro para eliminar.");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);

    }

    // EQUIPOS_JUGADORES QUE TIENE UN EQUIPO
    @GetMapping("equipos/{id}")
    public  ResponseEntity<?>equipoGrupoByFase(@PathVariable Long id ) {

        List<EquipoJugador> listaEquiposJugadores;
        Map<String, Object> response = new HashMap<>();

        try {
            listaEquiposJugadores = equipoJugadorServiceServiceEntity.listarByEntity(id);

        } catch ( DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos." );
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (listaEquiposJugadores== null || listaEquiposJugadores.isEmpty()) {
            response.put("mensaje", "No hay jugadores para el equipo.");
            return new ResponseEntity<Map<String, Object>> (response, HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<>(listaEquiposJugadores, HttpStatus.OK);
    }

}
